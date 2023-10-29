package com.github.pwilcz.price.domain.query;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PriceQuery(
        @NotNull
        BigDecimal price,
        @Min(0)
        int amount,
        DiscountQuery discount
) {
}

