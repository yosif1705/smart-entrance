package com.smartentrance.backend.dto.finance;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

public record FinancialSummary(
        @Schema(description = "Total money available across all funds", example = "5430.50")
        BigDecimal totalBalance,

        @Schema(description = "Breakdown for Repair Fund")
        FundBreakdown repairFund,

        @Schema(description = "Breakdown for Maintenance Fund")
        FundBreakdown maintenanceFund,

        @Schema(description = "Money held in cash by manager", example = "200.00")
        BigDecimal cashOnHands,

        @Schema(description = "Money in bank accounts/Stripe", example = "5230.50")
        BigDecimal bankAccounts
) {
    public record FundBreakdown(
            @Schema(example = "1000.00") BigDecimal income,
            @Schema(example = "200.00") BigDecimal expense,
            @Schema(example = "800.00") BigDecimal balance
    ) {}
}