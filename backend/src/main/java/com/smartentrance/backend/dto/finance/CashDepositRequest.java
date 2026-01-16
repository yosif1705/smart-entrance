package com.smartentrance.backend.dto.finance;

import com.smartentrance.backend.model.enums.FundType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record CashDepositRequest(
        @Schema(description = "Amount given in cash", example = "20.00")
        @NotNull @Positive BigDecimal amount,

        @Schema(description = "Which fund is this for?", example = "MAINTENANCE")
        FundType fundType,

        @Schema(description = "Optional note", example = "Left at the reception")
        String note
) {}