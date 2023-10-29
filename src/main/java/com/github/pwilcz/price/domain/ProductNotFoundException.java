package com.github.pwilcz.price.domain;

import java.text.MessageFormat;
import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(UUID id) {
        super(MessageFormat.format("Product with id {0} does not exists", id));
    }
}
