package com.smartentrance.backend.dto.file;

import io.swagger.v3.oas.annotations.media.Schema;

public record FileUploadResponse(
        @Schema(example = "scan_123.pdf")
        String fileName,

        @Schema(example = "http://host/api/uploads/files/scan_123.pdf")
        String url,

        @Schema(example = "application/pdf")
        String type,

        @Schema(example = "102400")
        long size
) {}