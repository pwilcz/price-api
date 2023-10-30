package com.github.pwilcz.price.domain;

import com.github.pwilcz.price.domain.query.PriceQuery;
import com.github.pwilcz.price.domain.query.PriceResponse;
import com.github.pwilcz.price.domain.query.ProductQuery;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/calculations")
@Tag(name = "Price Calculations")
class PriceController {

    private final PriceFacade facade;

    @PostMapping("/price-commands")
    ResponseEntity<PriceResponse> calculatePrice(@Valid @RequestBody PriceQuery query) {
        return ResponseEntity.ok(facade.calculate(query));
    }

    @PostMapping("/product-commands")
    ResponseEntity<PriceResponse> calculateProductPrice(@Valid @RequestBody ProductQuery query) {
        return ResponseEntity.ok(facade.calculate(query));
    }
}
