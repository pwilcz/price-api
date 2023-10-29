package com.github.pwilcz.price.domain;

import com.github.pwilcz.price.domain.query.PriceQuery;
import com.github.pwilcz.price.domain.query.PriceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor
class PriceFacade {

    private final PriceCalculator priceCalculator;
    private final DiscountStrategyFactory discountStrategyFactory;

    PriceResponse calculate(PriceQuery query) {
        log.info("Processing price query: {}", query);
        ProductInfo productInfo = new ProductInfo(query.price(), query.amount());
        BigDecimal totalPrice = priceCalculator.calculate(productInfo, discountStrategyFactory.create(query.discount()));
        return new PriceResponse(totalPrice);
    }
}
