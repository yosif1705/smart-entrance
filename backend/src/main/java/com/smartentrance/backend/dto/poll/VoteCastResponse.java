package com.smartentrance.backend.dto.poll;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.Instant;

public record VoteCastResponse(
        @Schema(example = "555")
        Integer id,
        @Schema(example = "12")
        Integer unitNumber,
        @Schema(example = "2024-05-02T10:00:00Z")
        Instant votedAt
){}