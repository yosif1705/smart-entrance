package com.smartentrance.backend.dto.finance;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record BankDepositRequest(
        @Schema(description = "Amount transferred", example = "120.50")
        @NotNull @Positive BigDecimal amount,

        @Schema(description = "Bank reference number / Description", example = "Payment for Apt 12")
        String transactionReference,

        @Schema(description = "Screenshot or PDF of the bank transfer", example = "http://host/api/uploads/files/proof_123.pdf")
        String proofUrl
) {}