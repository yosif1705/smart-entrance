package com.smartentrance.backend.dto.finance;

import com.smartentrance.backend.model.enums.FundType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.Instant;

public record BuildingExpenseResponse(
        @Schema(example = "55")
        Long id,
        @Schema(example = "250.00")
        BigDecimal amount,
        @Schema(example = "Elevator repair service")
        String description,
        @Schema(example = "REPAIR")
        FundType fundType,
        @Schema(example = "http://.../invoice.pdf")
        String documentUrl,
        @Schema(example = "2024-02-10T14:30:00Z")
        Instant expenseDate,
        @Schema(example = "Ivan Petrov")
        String createdBy
) {}