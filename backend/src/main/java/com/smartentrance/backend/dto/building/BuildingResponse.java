package com.smartentrance.backend.dto.building;

import io.swagger.v3.oas.annotations.media.Schema;

public record BuildingResponse(
        @Schema(example = "1")
        Integer id,

        @Schema(example = "Sunrise Residence")
        String name,

        @Schema(example = "bul. Bulgaria 110, Sofia")
        String address,

        @Schema(example = "A")
        String entrance,

        @Schema(example = "24")
        Integer totalUnits,

        @Schema(example = "BG98UBBS80021234567890")
        String iban,

        @Schema(description = "Details of the current House Manager")
        ManagerInfo managerInfo
) {
    public record ManagerInfo(
            @Schema(example = "5")
            Integer id,
            @Schema(example = "Ivan")
            String firstName,
            @Schema(example = "Petrov")
            String lastName,
            @Schema(example = "ivan.petrov@example.com")
            String email
    ) {}
}