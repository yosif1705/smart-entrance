package com.smartentrance.backend.dto.poll;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record VoteCastRequest(
        @Schema(description = "ID of the selected option", example = "101")
        @NotNull(message = "You must select an option to vote")
        Integer optionId,

        @Schema(description = "ID of the unit casting the vote", example = "15")
        @NotNull(message = "You must specify the unit which is voting.")
        Long unitId
) {}