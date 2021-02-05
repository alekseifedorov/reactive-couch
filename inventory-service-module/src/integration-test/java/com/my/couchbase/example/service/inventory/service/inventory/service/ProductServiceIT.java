package com.my.couchbase.example.service.inventory.service.inventory.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.my.couchbase.example.service.inventory.dto.Product;
import com.my.couchbase.example.service.inventory.service.inventory.IntegrationTestBase;
import java.io.IOException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class ProductServiceIT extends IntegrationTestBase {

    private Product product;

    @Test
    public void whenSendingGet_thenMessageIsReturned() throws IOException {
        productService.create(Product.builder().name("abcd").quantity(1).build())
            .as(StepVerifier::create)
            .assertNext(it -> {
                product = it;
            }).verifyComplete();

        productService.findById(product.getId())
            .as(StepVerifier::create)
            .assertNext(it -> {
                Assertions.assertThat(it).isNotNull();
            }).verifyComplete();
    }
}