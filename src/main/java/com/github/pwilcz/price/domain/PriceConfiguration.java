package com.github.pwilcz.price.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class PriceConfiguration {

    @Bean
    PriceFacade priceFacade(ProductPriceClient productPriceClient) {
        PriceCalculator priceCalculator = new PriceCalculator();
        DiscountStrategyFactory discountStrategyFactory = new DiscountStrategyFactory();
        return new PriceFacade(priceCalculator, discountStrategyFactory, productPriceClient);
    }
}
