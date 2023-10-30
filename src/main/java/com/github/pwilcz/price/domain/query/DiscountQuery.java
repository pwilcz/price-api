package com.github.pwilcz.price.domain.query;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DiscountQuery(
        @NotNull
        DiscountPolicyType type,
        @Min(0)
        @Max(100)
        @NotNull
        int value
) {
    public enum DiscountPolicyType {
        PERCENTAGE,
        AMOUNT
    }
}
