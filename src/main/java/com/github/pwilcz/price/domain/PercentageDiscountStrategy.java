package com.github.pwilcz.price.domain;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
class PercentageDiscountStrategy implements DiscountStrategy {

    final int value;


    @Override
    public BigDecimal calculateDiscount(ProductInfo productInfo, BigDecimal basePrice) {
        return BigDecimal.valueOf(value / 100.0).multiply(basePrice);
    }
}
