package com.smartentrance.backend.dto.building;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record BuildingCreateRequest(
        @Schema(description = "Full address of the building", example = "bul. Bulgaria 110, Sofia")
        @NotBlank(message = "Address is required")
        String address,

        @Schema(description = "Entrance identifier (A, B, V...)", example = "A")
        @NotBlank(message = "Entrance is required")
        String entrance,

        @Schema(description = "Friendly name for the condominium", example = "Sunrise Residence")
        @NotBlank(message = "Building name is required")
        String name,

        @Schema(description = "Total number of apartments/offices", example = "24")
        @Min(value = 1, message = "The building must have at least one unit")
        @Max(value = 100, message = "The building cannot have more than 100 units")
        Integer totalUnits,

        @Schema(description = "Bank Account IBAN for the building fund", example = "BG98UBBS80021234567890")
        String iban
) {}