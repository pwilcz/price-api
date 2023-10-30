package com.github.pwilcz.price;


import com.github.pwilcz.price.domain.ProductPriceClient;
import com.github.pwilcz.price.domain.query.DiscountQuery;
import com.github.pwilcz.price.domain.query.PriceQuery;
import com.github.pwilcz.price.domain.query.ProductQuery;
import com.github.pwilcz.price.infra.product.MockPriceClient;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
class PriceIntegrationTests {

    static UUID uuid = UUID.randomUUID();

    @TestConfiguration
    static class ProductTestConfiguration {
        @Bean
        public ProductPriceClient productPriceClient() {
            return new MockPriceClient(Map.of(uuid, BigDecimal.valueOf(15)));
        }
    }

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        RestAssuredMockMvc.mockMvc(mockMvc);
        RestAssuredMockMvc.config().objectMapperConfig(
                ObjectMapperConfig.objectMapperConfig()
                        .jackson2ObjectMapperFactory(
                                ObjectMapperConfig.objectMapperConfig().jackson2ObjectMapperFactory()
                        )
        );
    }


    @Test
    void priceCalculationTest() {

        PriceQuery query = new PriceQuery(
                BigDecimal.valueOf(10),
                4,
                new DiscountQuery(DiscountQuery.DiscountPolicyType.PERCENTAGE, 10)
        );

        PriceEndpointOperations.calculatePrice(query)
                .status(HttpStatus.OK)
                .and()
                .body("totalPrice", Matchers.equalTo(36.0f));
    }

    @Test
    void productCalculationTest() {
        ProductQuery query = new ProductQuery(
                uuid,
                4,
                new DiscountQuery(DiscountQuery.DiscountPolicyType.AMOUNT, 5)
        );

        PriceEndpointOperations.calculateProductPrice(query)
                .and()
                .status(HttpStatus.OK)
                .and()
                .body("totalPrice", Matchers.equalTo(51.0f));
    }

    @Test
    void productNotFoundTest() {
        ProductQuery query = new ProductQuery(
                UUID.randomUUID(),
                4,
                new DiscountQuery(DiscountQuery.DiscountPolicyType.AMOUNT, 5)
        );

        PriceEndpointOperations.calculateProductPrice(query)
                .and()
                .status(HttpStatus.NOT_FOUND);
    }
}
