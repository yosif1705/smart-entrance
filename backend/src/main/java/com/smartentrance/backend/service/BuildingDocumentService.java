package com.smartentrance.backend.service;

import com.smartentrance.backend.dto.document.CreateDocumentRequest;
import com.smartentrance.backend.dto.document.DocumentResponse;
import com.smartentrance.backend.model.Building;
import com.smartentrance.backend.model.BuildingDocument;
import com.smartentrance.backend.model.User;
import com.smartentrance.backend.model.enums.DocumentType;
import com.smartentrance.backend.repository.BuildingRepository;
import com.smartentrance.backend.repository.DocumentRepository;
import com.smartentrance.backend.security.BuildingSecurity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildingDocumentService {

    private final DocumentRepository documentRepository;
    private final BuildingRepository buildingRepository;
    private final BuildingSecurity buildingSecurity;

    @PreAuthorize("@buildingSecurity.hasAccess(#buildingId, principal.user)")
    public List<DocumentResponse> getDocumentsForBuilding(Integer buildingId, DocumentType type, User user) {
        List<BuildingDocument> docs;
        boolean isManager = buildingSecurity.isManager(buildingId, user);

        if (isManager) {
            if (type != null) {
                docs = documentRepository.findAllByBuildings_IdAndTypeOrderByCreatedAtDesc(buildingId, type);
            } else {
                docs = documentRepository.findAllByBuildings_IdOrderByCreatedAtDesc(buildingId);
            }
        } else {
            if (type != null) {
                docs = documentRepository.findAllByBuildings_IdAndTypeAndIsVisibleToResidentsTrueOrderByCreatedAtDesc(buildingId, type);
            } else {
                docs = documentRepository.findAllByBuildings_IdAndIsVisibleToResidentsTrueOrderByCreatedAtDesc(buildingId);
            }
        }

        return docs.stream().map(this::mapToResponse).toList();
    }

    @Transactional
    @PreAuthorize("@buildingSecurity.isManager(#buildingId, principal.user)")
    public void createDocument(Integer buildingId, CreateDocumentRequest req, User uploader) {
        Building primaryBuilding = buildingRepository.findById(buildingId)
                .orElseThrow(() -> new EntityNotFoundException("Building not found"));

        List<Building> targetBuildings = new ArrayList<>();
        targetBuildings.add(primaryBuilding);

        if (req.sharedBuildingIds() != null && !req.sharedBuildingIds().isEmpty()) {

            for (Integer sharedId : req.sharedBuildingIds()) {

                if (sharedId.equals(buildingId)) continue;

                boolean isManager = buildingRepository.existsByIdAndManagerId(sharedId, uploader.getId());

                if (!isManager) {
                    throw new org.springframework.security.access.AccessDeniedException(
                            "You cannot upload documents to building ID " + sharedId + " because you are not its manager."
                    );
                }

                buildingRepository.findById(sharedId).ifPresent(targetBuildings::add);
            }
        }

        BuildingDocument doc = new BuildingDocument();
        doc.setBuildings(targetBuildings);
        doc.setUploadedBy(uploader);
        doc.setTitle(req.title());
        doc.setDescription(req.description());
        doc.setType(req.type());
        doc.setFileUrl(req.fileUrl());
        doc.setVisibleToResidents(req.isVisibleToResidents());

        documentRepository.save(doc);
    }

    @PreAuthorize("@buildingSecurity.canManageDocument(#documentId, principal.user)")
    @Transactional
    public void deleteDocument(Long documentId) {
        documentRepository.deleteById(documentId);
    }

    private DocumentResponse mapToResponse(BuildingDocument doc) {
        String uploaderName = (doc.getUploadedBy() != null)
                ? doc.getUploadedBy().getFirstName() + " " + doc.getUploadedBy().getLastName()
                : "System";

        return new DocumentResponse(
                doc.getId(), doc.getTitle(), doc.getDescription(), doc.getType(),
                doc.getFileUrl(), uploaderName, doc.isVisibleToResidents(), doc.getCreatedAt()
        );
    }
}