package com.smartentrance.backend.dto.finance;

import com.smartentrance.backend.model.enums.FundType;
import com.smartentrance.backend.model.enums.PaymentMethod;
import com.smartentrance.backend.model.enums.TransactionStatus;
import com.smartentrance.backend.model.enums.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.Instant;

public record TransactionResponse(
        @Schema(example = "889")
        Long id,
        @Schema(example = "50.00")
        BigDecimal amount,
        @Schema(example = "PAYMENT")
        TransactionType type,
        @Schema(example = "MAINTENANCE")
        FundType fundType,
        @Schema(example = "STRIPE")
        PaymentMethod paymentMethod,
        @Schema(example = "Monthly fee payment")
        String description,
        @Schema(example = "CONFIRMED")
        TransactionStatus transactionStatus,
        @Schema(example = "http://.../receipt.pdf")
        String documentUrl,
        @Schema(example = "http://stripe.com/receipt...")
        String externalDocumentUrl,
        @Schema(example = "2026-01-01T12:00:00Z")
        Instant createdAt,
        @Schema(example = "15")
        Long unitId,
        @Schema(example = "12")
        Integer unitNumber
) {}