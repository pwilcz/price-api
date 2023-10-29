package com.github.pwilcz.price.domain.query;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ProductQuery(
        @NotNull
        UUID productId,
        @Min(0)
        int amount,
        DiscountQuery discount
) {
}

