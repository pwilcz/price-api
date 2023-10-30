package com.github.pwilcz.price.infra.product;

import com.github.pwilcz.price.domain.ProductPriceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;


@Configuration
@Slf4j
public class ProductConfiguration {

    static Map<UUID, BigDecimal> IN_MEMORY_STORE = Map.of(
            UUID.fromString("39a8ce44-13a5-4fd6-a6e7-cbe08a2e8ec7"), BigDecimal.valueOf(1d),
            UUID.fromString("4eb94e83-ecd0-49c4-aee8-17b023f466dc"), BigDecimal.valueOf(10d),
            UUID.fromString("f6fbc666-9814-40ca-b68e-0889fa970621"), BigDecimal.valueOf(100d)
    );


    @Bean
    public ProductPriceClient mockProductPriceClient() {
        log.info("Creating mock product client with products {}", IN_MEMORY_STORE);
        return new MockPriceClient(IN_MEMORY_STORE);
    }

}
