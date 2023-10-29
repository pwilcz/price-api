package com.github.pwilcz.price.domain;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
class PriceCalculator {

    BigDecimal calculate(ProductInfo productInfo, DiscountStrategy discountStrategy) {
        BigDecimal basePrice = calculateProductPrice(productInfo);
        log.debug("Calculated base price: {}", basePrice);

        BigDecimal discount = calculateDiscount(productInfo, discountStrategy, basePrice);
        log.debug("Applying discount: {}", discount);

        BigDecimal finalPrice = basePrice.subtract(discount);
        log.info("Calculated price: {}", finalPrice);
        return finalPrice;
    }

    private BigDecimal calculateDiscount(ProductInfo productInfo, DiscountStrategy discountStrategy, BigDecimal basePrice) {
        return minimum(basePrice, discountStrategy.calculateDiscount(productInfo, basePrice));
    }

    private BigDecimal calculateProductPrice(ProductInfo productInfo) {
        return BigDecimal.valueOf(productInfo.amount()).multiply(productInfo.price());
    }

    private BigDecimal minimum(BigDecimal a, BigDecimal b) {
        return a.min(b);
    }
}
