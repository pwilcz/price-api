package com.github.pwilcz.price.domain.query;

import java.math.BigDecimal;

public record PriceResponse(
        BigDecimal totalPrice
) {
}
