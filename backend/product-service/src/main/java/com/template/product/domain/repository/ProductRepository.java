package com.template.product.domain.repository;

import com.template.product.domain.model.Product;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    Optional<Product> findById(UUID id);
    Product save(Product product);
    void deleteById(UUID id);
}