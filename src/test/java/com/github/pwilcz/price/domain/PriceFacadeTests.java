package com.github.pwilcz.price.domain;

import com.github.pwilcz.price.domain.query.DiscountQuery;
import com.github.pwilcz.price.domain.query.PriceQuery;
import com.github.pwilcz.price.domain.query.PriceResponse;
import com.github.pwilcz.price.domain.query.ProductQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

class PriceFacadeTests {

    UUID uuid = UUID.randomUUID();
    ProductPriceClient productPriceClient = Mockito.mock(ProductPriceClient.class);
    PriceFacade priceFacade = new PriceConfiguration().priceFacade(productPriceClient);

    @BeforeEach
    void setup() {
        Mockito.when(productPriceClient.getProductPrice(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(productPriceClient.getProductPrice(Mockito.eq(uuid))).thenReturn(Optional.of(BigDecimal.valueOf(100d)));
    }

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

    @Test
    void testCalculatesTotal_whenProductId() {

        //given
        ProductQuery query = new ProductQuery(uuid, 5, null);

        //when
        PriceResponse response = priceFacade.calculate(query);

        //then
        assertCalculatedPrice(500d, response);
    }

    @Test
    void testCalculatesTotal_whenProductIdNotFount() {

        //given
        ProductQuery query = new ProductQuery(UUID.randomUUID(), 5, null);

        //when

        Assertions.assertThrows(
                ProductNotFoundException.class,
                () -> priceFacade.calculate(query)
        );

    }

    private static void assertCalculatedPrice(double val, PriceResponse response) {
        Assertions.assertEquals(BigDecimal.valueOf(val).stripTrailingZeros(), response.totalPrice().stripTrailingZeros());
    }

}
