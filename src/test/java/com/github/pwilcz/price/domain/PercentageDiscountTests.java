package com.github.pwilcz.price.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class PercentageDiscountTests extends DiscountStrategyTests {


    @ParameterizedTest
    @MethodSource("percentageDiscountCalculationParamsProvider")
    void testPercentageDiscountCalculation(ProductInfo productInfo, int percentage, String expected) {
        PercentageDiscountStrategy calculator = new PercentageDiscountStrategy(percentage);
        calculateAndAssert(calculator, productInfo, expected);
    }


    static Stream<Arguments> percentageDiscountCalculationParamsProvider() {
        return Stream.of(
                Arguments.of(productInfo(10.0, 10), 1, "1.000"),
                Arguments.of(productInfo(10.0, 10), 5, "5.0"),
                Arguments.of(productInfo(10.0, 10), 20, "20.0"),
                Arguments.of(productInfo(10.0, 10), 50, "50.0"),
                Arguments.of(productInfo(10.0, 10), 0, "0.0"),
                Arguments.of(productInfo(10.0, 10), 100, "100.0")
        );
    }
}
