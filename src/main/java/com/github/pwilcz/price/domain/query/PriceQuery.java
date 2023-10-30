package com.github.pwilcz.price.domain.query;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
public record PriceQuery(
        @NotNull
        @DecimalMin("0")
        BigDecimal price,
        @NotNull
        @Min(0)
        int amount,
        DiscountQuery discount
) {
}

