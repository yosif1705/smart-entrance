package com.smartentrance.backend.dto.document;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

public record ReceiptDetails(
        @Schema(example = "9941")
        Long receiptNumber,
        @Schema(example = "2024-01-15")
        String issueDate,

        @Schema(example = "Georgi Ivanov")
        String payerName,
        @Schema(example = "12")
        Integer payerUnit,

        @Schema(example = "bul. Bulgaria 110, Sofia")
        String buildingAddress,
        @Schema(example = "Ivan Petrov")
        String managerName,

        @Schema(example = "50.00")
        BigDecimal amount,
        @Schema(example = "EUR")
        String currency,
        @Schema(example = "Monthly Fee - January")
        String reason,

        @Schema(example = "")
        String documentUrl
) {}