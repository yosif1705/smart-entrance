package com.smartentrance.backend.dto.document;

import com.smartentrance.backend.model.enums.DocumentType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.Instant;

public record DocumentResponse(
        @Schema(example = "101")
        Long id,

        @Schema(example = "House Rules 2026")
        String title,

        @Schema(example = "Updated rules regarding noise and pets")
        String description,

        @Schema(example = "RULEBOOK")
        DocumentType type,

        @Schema(example = "https://cdn.smartentrance.com/files/house_rules_2026.pdf")
        String fileUrl,

        @Schema(example = "Ivan Petrov")
        String uploaderName,

        @Schema(example = "true")
        boolean isVisible,

        @Schema(example = "2026-01-01T10:00:00Z")
        Instant createdAt
) {}