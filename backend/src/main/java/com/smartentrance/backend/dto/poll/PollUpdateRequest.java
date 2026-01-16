package com.smartentrance.backend.dto.poll;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;

import java.time.Instant;

public record PollUpdateRequest(

        @Schema(example = "Updated: Paint color selection")
        String title,

        @Schema(example = "We added a Blue option")
        String description,

        @Schema(example = "2026-06-01T08:00:00Z")
        @FutureOrPresent(message = "Start time should be in the present or future")
        Instant startAt,

        @Schema(example = "2026-06-15T20:00:00Z")
        @Future(message = "End time should be in the future")
        Instant endAt
) {}