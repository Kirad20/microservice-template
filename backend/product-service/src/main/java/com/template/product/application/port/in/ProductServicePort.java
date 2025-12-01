package com.template.product.application.port.in;

import com.template.product.domain.model.Product;

import java.util.UUID;

public interface ProductServicePort {
    Product getProductById(UUID id);
    Product createProduct(Product product);
}