package com.smartentrance.backend.dto.notice;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.Instant;

public record NoticeResponse(
        @Schema(example = "10")
        Integer id,
        @Schema(example = "5")
        Integer createdByUserId,
        @Schema(example = "General Assembly Meeting")
        String title,
        @Schema(example = "Discussion about roof repairs")
        String description,
        @Schema(example = "Main Lobby")
        String location,
        @Schema(example = "2026-12-01T18:30:00Z")
        Instant noticeDateTime
) {}