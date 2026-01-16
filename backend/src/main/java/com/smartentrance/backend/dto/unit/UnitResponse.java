package com.smartentrance.backend.dto.unit;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

public record UnitResponse(
        @Schema(example = "15")
        Long id,
        @Schema(example = "12")
        Integer unitNumber,
        @Schema(example = "85.5")
        BigDecimal area,
        @Schema(example = "3")
        Integer residents,
        @Schema(example = "8X21A9BB")
        String accessCode,
        @Schema(example = "true")
        boolean isVerified,
        @Schema(example = "-20.00")
        BigDecimal balance,
        @Schema(example = "true")
        boolean hasPendingPayments,
        BuildingInfo buildingInfo,
        OwnerInfo ownerInfo
) {
    public record BuildingInfo(
            @Schema(example = "1") Integer id,
            @Schema(example = "Sunrise Residence") String name,
            @Schema(example = "bul. Bulgaria 110") String address
    ) {}
    public record OwnerInfo (
            @Schema(example = "5") Integer id,
            @Schema(example = "Georgi") String firstName,
            @Schema(example = "Ivanov") String lastName,
            @Schema(example = "geo@example.com") String email
    ) {}
}