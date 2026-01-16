package com.smartentrance.backend.dto.poll;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.List;

public record PollCreateRequest(
        @Schema(description = "Question or topic", example = "Should we paint the entrance green?")
        @NotBlank(message = "Title is required")
        String title,

        @Schema(description = "More details", example = "Cost will be 50 EUR per apartment")
        String description,

        @Schema(description = "When voting starts", example = "2026-05-01T08:00:00Z")
        @NotNull(message = "Start date is required")
        Instant startAt,

        @Schema(description = "When voting ends", example = "2026-05-10T20:00:00Z")
        @NotNull(message = "End date is required")
        @Future(message = "End date must be in the future")
        Instant endAt,

        @Schema(description = "Possible answers", example = "[\"Yes\", \"No\", \"Abstain\"]")
        @NotNull(message = "Options are required")
        @Size(min = 2, message = "At least two options are required")
        List<String> options
) {}