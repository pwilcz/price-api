package com.github.pwilcz.price.infra.product;

import com.github.pwilcz.price.domain.ProductPriceClient;

import java.util.Map;

public class ProductConfiguration {

    public ProductPriceClient mockProductPriceClient() {
        return new MockPriceClient(
                Map.of(

                )
        );
    }

}
