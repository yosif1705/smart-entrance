package com.smartentrance.backend.dto.notice;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;

import java.time.Instant;

public record NoticeUpdateRequest(
        @Schema(example = "Urgent: Roof Repair Meeting")
        String title,

        @Schema(example = "Updated time due to holidays")
        String description,

        @Schema(example = "Floor 2 Hallway")
        String location,

        @Schema(example = "2026-12-05T19:00:00Z")
        @FutureOrPresent(message = "The notice date and time must be in the future or present")
        Instant noticeDateTime
) {}