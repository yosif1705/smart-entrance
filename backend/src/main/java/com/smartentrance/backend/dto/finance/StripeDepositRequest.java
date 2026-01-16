package com.smartentrance.backend.dto.finance;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record StripeDepositRequest(
        @Schema(description = "Amount to deposit", example = "50.00")
        @Positive BigDecimal amount
) {}