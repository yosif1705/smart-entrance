package com.smartentrance.backend.dto.building;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

public record UpdateBudgetRequest(
        @Schema(description = "Monthly target amount for Repair Fund", example = "500.00")
        BigDecimal repairBudget,

        @Schema(description = "Monthly target for Maintenance Fund (Cleaning, Elevator, etc.)", example = "250.00")
        BigDecimal maintenanceBudget,

        @Schema(description = "URL to the protocol document approving these budgets", example = "https://cdn.smartentrance.com/files/protocol_2026.pdf")
        String protocolFileUrl
) {}