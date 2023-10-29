package com.github.pwilcz.price.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class AmountDiscountTests extends DiscountStrategyTests {


    @ParameterizedTest
    @MethodSource("amountDiscountCalculationParamsProvider")
    void testAmountDiscountCalculation(ProductInfo productInfo, int percentage, String expected) {
        AmountDiscountStrategy calculator = new AmountDiscountStrategy(percentage);
        calculateAndAssert(calculator, productInfo, expected);
    }


    static Stream<Arguments> amountDiscountCalculationParamsProvider() {
        return Stream.of(
                Arguments.of(productInfo(10.0, 0), 10, "0.0"),
                Arguments.of(productInfo(10.0, 1), 10, "0.0"),
                Arguments.of(productInfo(10.0, 2), 10, "2.0"),
                Arguments.of(productInfo(10.0, 2), 0, "0.0"),
                Arguments.of(productInfo(10.0, 5), 10, "20.0"),
                Arguments.of(productInfo(10.0, 10), 10, "90.0"),
                Arguments.of(productInfo(10.0, 11), 10, "110.0"),
                Arguments.of(productInfo(10.0, 12), 10, "120.0")
        );
    }
}
