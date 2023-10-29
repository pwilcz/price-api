package com.github.pwilcz.price.domain;

import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;

abstract class DiscountStrategyTests {

    protected void calculateAndAssert(DiscountStrategy discountStrategy, ProductInfo productInfo, String expected) {
        //when
        BigDecimal total = productInfo.price().multiply(BigDecimal.valueOf(productInfo.amount()));
        BigDecimal discount = discountStrategy.calculateDiscount(productInfo, total);

        //then
        Assertions.assertEquals(new BigDecimal(expected).stripTrailingZeros(), discount.stripTrailingZeros());
    }


    protected static ProductInfo productInfo(double price, int amount) {
        return new ProductInfo(
                BigDecimal.valueOf(price),
                amount
        );
    }

}
