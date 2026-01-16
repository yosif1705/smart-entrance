package com.smartentrance.backend.dto.notice;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record NoticeCreateRequest(
        @Schema(description = "Notice headline", example = "General Assembly Meeting")
        @NotBlank(message = "Title is required")
        String title,

        @Schema(description = "Detailed information", example = "We will discuss the roof repairs.")
        String description,

        @Schema(description = "Where will it happen?", example = "Main Lobby")
        @NotBlank(message = "Place is required")
        String location,

        @Schema(description = "Date and time of event", example = "2026-01-01T18:30:00Z")
        @NotNull(message = "Date and time is required")
        @FutureOrPresent(message = "Notice date and time must be in the present or future")
        Instant noticeDateTime
) {}