package com.github.pwilcz.price.domain;

import com.github.pwilcz.price.domain.query.DiscountQuery;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
class DiscountStrategyFactory {


    DiscountStrategy create(DiscountQuery query) {
        return Optional.ofNullable(query)
                .map(this::createStrategy)
                .orElse(DiscountStrategy.NO_DISCOUNT);
    }


    private DiscountStrategy createStrategy(DiscountQuery query) {
        log.info("Creating strategy for discount query {}", query.type());
        return switch (query.type()) {
            case AMOUNT -> new AmountDiscountStrategy(query.value());
            case PERCENTAGE -> new PercentageDiscountStrategy(query.value());
        };
    }
}
