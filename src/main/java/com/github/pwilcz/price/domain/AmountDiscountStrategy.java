package com.github.pwilcz.price.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor
class AmountDiscountStrategy implements DiscountStrategy {

    final int discountIncrement;

    @Override
    public BigDecimal calculateDiscount(ProductInfo productInfo, BigDecimal basePrice) {
        int discountPercentage = getDiscountPercentage(productInfo.amount(), discountIncrement);
        log.debug("Calculated discount percentage: {}", discountPercentage);
        return basePrice.multiply(BigDecimal.valueOf(discountPercentage / 100.0));
    }

    int getDiscountPercentage(int amount, int increment) {
        int percentage = increment * (amount - 1);
        return Math.min(Math.max(0, percentage), 100);
    }
}
