package com.smartentrance.backend.dto.poll;

import com.smartentrance.backend.dto.enums.PollStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.List;

public record PollResponse(
        @Schema(example = "42")
        Integer id,
        @Schema(example = "5")
        Integer createdByUserId,
        @Schema(example = "Paint the entrance?")
        String title,
        @Schema(example = "Should we paint it green?")
        String description,
        @Schema(example = "2026-05-01T08:00:00Z")
        Instant startAt,
        @Schema(example = "2026-05-10T20:00:00Z")
        Instant endAt,
        @Schema(example = "ACTIVE")
        PollStatus status,
        @Schema(example = "15")
        Long totalVotes,
        @Schema(example = "24")
        Integer totalEligibleVoters,
        @Schema(description = "ID of option user voted for, or null", example = "101")
        Integer userVotedOptionId,
        @Schema(description = "List of options with current vote counts")
        List<PollOptionResponse> options
) {
    public record PollOptionResponse(
            @Schema(example = "101") Integer id,
            @Schema(example = "Yes") String text,
            @Schema(example = "10") Long voteCount
    ) {}
}