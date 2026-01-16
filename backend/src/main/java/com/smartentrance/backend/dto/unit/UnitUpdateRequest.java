package com.smartentrance.backend.dto.unit;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;

public record UnitUpdateRequest(
        @Schema(description = "Corrected area size", example = "86.0")
        @DecimalMin(value = "0.0", message = "Area must be positive")
        BigDecimal area,

        @Schema(description = "Updated resident count", example = "4")
        @Min(value = 0, message = "Residents count cannot be negative")
        Integer residentsCount
) {}