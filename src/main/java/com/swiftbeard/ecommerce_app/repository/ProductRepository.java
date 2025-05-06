package com.swiftbeard.ecommerce_app.repository;

import java.util.UUID;

import com.swiftbeard.ecommerce_app.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, UUID> {
}
