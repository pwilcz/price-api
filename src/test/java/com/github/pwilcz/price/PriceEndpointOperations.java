package com.github.pwilcz.price;

import com.github.pwilcz.price.domain.query.PriceQuery;
import com.github.pwilcz.price.domain.query.ProductQuery;
import io.restassured.module.mockmvc.response.ValidatableMockMvcResponse;
import org.springframework.http.MediaType;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

class PriceEndpointOperations {


    static ValidatableMockMvcResponse calculatePrice(PriceQuery query) {
        return given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(query)
                .when()
                .post("/calculations/price-commands")
                .then()
                .log()
                .ifError()
                .and();
    }

    static ValidatableMockMvcResponse calculateProductPrice(ProductQuery query) {
        return given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(query)
                .when()
                .post("/calculations/product-commands")
                .then()
                .log()
                .ifError()
                .and();
    }

}
