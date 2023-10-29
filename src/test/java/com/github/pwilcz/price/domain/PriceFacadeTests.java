package com.github.pwilcz.price.domain;

import com.github.pwilcz.price.domain.query.DiscountQuery;
import com.github.pwilcz.price.domain.query.PriceQuery;
import com.github.pwilcz.price.domain.query.PriceResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class PriceFacadeTests {

    PriceFacade priceFacade = new PriceConfiguration().priceFacade();

    @Test
    void testCalculatesTotal_whenNoDiscount() {

        //given
        PriceQuery query = new PriceQuery(BigDecimal.valueOf(10d), 5, null);

        //when
        PriceResponse response = priceFacade.calculate(query);

        //then
        assertCalculatedPrice(50d, response);
    }

    @Test
    void testCalculatesTotal_whenAmountDiscount() {

        //given
        PriceQuery query = new PriceQuery(BigDecimal.valueOf(10d), 5, new DiscountQuery(DiscountQuery.DiscountPolicyType.AMOUNT, 5));

        //when
        PriceResponse response = priceFacade.calculate(query);

        //then
        assertCalculatedPrice(40d, response);
    }

    @Test
    void testCalculatesTotal_whenPercentageDiscount() {

        //given
        PriceQuery query = new PriceQuery(BigDecimal.valueOf(10d), 5, new DiscountQuery(DiscountQuery.DiscountPolicyType.PERCENTAGE, 50));

        //when
        PriceResponse response = priceFacade.calculate(query);

        //then
        assertCalculatedPrice(25d, response);
    }

    private static void assertCalculatedPrice(double val, PriceResponse response) {
        Assertions.assertEquals(BigDecimal.valueOf(val).stripTrailingZeros(), response.totalPrice().stripTrailingZeros());
    }

}
