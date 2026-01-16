package com.smartentrance.backend.dto.document;

import com.smartentrance.backend.model.enums.DocumentType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record CreateDocumentRequest(
        @Schema(description = "Document title", example = "House Rules 2026")
        String title,

        @Schema(description = "Short description of content", example = "Updated rules regarding noise and pets")
        String description,

        @Schema(description = "Category of the document", example = "RULEBOOK")
        DocumentType type,

        @Schema(description = "URL to the uploaded file", example = "https://cdn.smartentrance.com/files/house_rules_2026.pdf")
        String fileUrl,

        @Schema(description = "Whether all residents can see this document", example = "true")
        boolean isVisibleToResidents,

        @Schema(description = "List of other Building IDs to share this document with (Manager only)", example = "[2, 3]")
        List<Integer> sharedBuildingIds
) {}