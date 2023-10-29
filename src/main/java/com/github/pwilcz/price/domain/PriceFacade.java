package com.github.pwilcz.price.domain;

import com.github.pwilcz.price.domain.query.DiscountQuery;
import com.github.pwilcz.price.domain.query.PriceQuery;
import com.github.pwilcz.price.domain.query.PriceResponse;
import com.github.pwilcz.price.domain.query.ProductQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor
class PriceFacade {

    private final PriceCalculator priceCalculator;
    private final DiscountStrategyFactory discountStrategyFactory;
    private final ProductPriceClient productPriceClient;

    PriceResponse calculate(PriceQuery query) {
        log.info("Processing price query: {}", query);
        ProductInfo productInfo = new ProductInfo(query.price(), query.amount());
        return calculateProductPrice(productInfo, query.discount());
    }

    PriceResponse calculate(ProductQuery query) {
        log.info("Processing product query: {}", query);
        BigDecimal productPrice = productPriceClient.getProductPrice(query.productId())
                .orElseThrow(() -> new ProductNotFoundException(query.productId()));
        log.info("Retrieved product price: {}", productPrice.toPlainString());

        ProductInfo productInfo = new ProductInfo(productPrice, query.amount());
        return calculateProductPrice(productInfo, query.discount());
    }

    private PriceResponse calculateProductPrice(ProductInfo productInfo, DiscountQuery discountQuery) {
        BigDecimal totalPrice = priceCalculator.calculate(productInfo, discountStrategyFactory.create(discountQuery));
        return new PriceResponse(totalPrice);
    }
}
