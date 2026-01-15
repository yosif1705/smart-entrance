package com.smartentrance.backend.dto.document;

import com.smartentrance.backend.model.enums.DocumentType;

import java.util.List;

public record CreateDocumentRequest(
        String title,
        String description,
        DocumentType type,
        String fileUrl,
        boolean isVisibleToResidents,
        List<Integer> sharedBuildingIds
) {}