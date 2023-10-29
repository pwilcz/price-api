package com.github.pwilcz.price.domain;

class PriceConfiguration {

    PriceFacade priceFacade() {
        PriceCalculator priceCalculator = new PriceCalculator();
        DiscountStrategyFactory discountStrategyFactory = new DiscountStrategyFactory();
        return new PriceFacade(priceCalculator, discountStrategyFactory);
    }
}