package com.github.pwilcz.price.infra.product;

import com.github.pwilcz.price.domain.ProductPriceClient;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class MockPriceClient implements ProductPriceClient {

    private final Map<UUID, BigDecimal> store;

    @Override

    public Optional<BigDecimal> getProductPrice(UUID productId) {
        return Optional.ofNullable(store.get(productId));
    }
}
