package com.smartentrance.backend.dto.finance;

import com.smartentrance.backend.model.enums.FundType;
import com.smartentrance.backend.model.enums.PaymentMethod;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record CreateExpenseRequest(
        @Schema(description = "Cost amount", example = "150.00")
        BigDecimal amount,

        @Schema(description = "What was paid for?", example = "Cleaning supplies")
        String description,

        @Schema(description = "Which fund covers this?", example = "MAINTENANCE")
        FundType fundType,

        @Schema(description = "Invoice or Receipt file", example = "files/invoice_123.jpg")
        String documentUrl,

        @Schema(description = "How was it paid?", example = "CASH")
        PaymentMethod paymentMethod
) {}