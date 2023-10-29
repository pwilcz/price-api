package com.github.pwilcz.price.domain;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface ProductPriceClient {

    Optional<BigDecimal> getProductPrice(UUID productId);

}
