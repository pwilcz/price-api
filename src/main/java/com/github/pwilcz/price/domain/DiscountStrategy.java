package com.github.pwilcz.price.domain;

import java.math.BigDecimal;

interface DiscountStrategy {

    DiscountStrategy NO_DISCOUNT = (ProductInfo productInfo, BigDecimal basePrice) -> BigDecimal.ZERO;

    BigDecimal calculateDiscount(ProductInfo productInfo, BigDecimal basePrice);
}
