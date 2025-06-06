package com.swiftbeard.ecommerce_app.service.impl;

import java.util.Optional;
import java.util.UUID;

import com.swiftbeard.ecommerce_app.entity.ProductEntity;
import com.swiftbeard.ecommerce_app.repository.ProductRepository;
import com.swiftbeard.ecommerce_app.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<ProductEntity> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public Optional<ProductEntity> getProduct(String id) {
        return repository
                .findById(UUID.fromString(id));
    }
}
