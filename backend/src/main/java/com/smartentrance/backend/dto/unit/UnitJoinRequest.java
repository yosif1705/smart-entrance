package com.smartentrance.backend.dto.unit;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UnitJoinRequest(
        @Schema(description = "Secure access code provided by Manager", example = "8X21A9BB")
        @NotBlank(message = "Access code is required")
        String accessCode,

        @Schema(description = "Number of people living in the unit", example = "3")
        @NotNull(message = "Residents count is required")
        Integer residentsCount,

        @Schema(description = "Area of the apartment in sq. meters", example = "85.5")
        @NotNull(message = "Area is required")
        BigDecimal area
) {}