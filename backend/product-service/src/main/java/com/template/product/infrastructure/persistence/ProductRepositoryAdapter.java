package com.template.product.infrastructure.persistence;

import com.template.product.domain.model.Product;
import com.template.product.domain.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class ProductRepositoryAdapter implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;

    public ProductRepositoryAdapter(JpaProductRepository jpaProductRepository) {
        this.jpaProductRepository = jpaProductRepository;
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return jpaProductRepository.findById(id).map(ProductEntity::toDomain);
    }

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = ProductEntity.fromDomain(product);
        return jpaProductRepository.save(productEntity).toDomain();
    }

    @Override
    public void deleteById(UUID id) {
        jpaProductRepository.deleteById(id);
    }
}