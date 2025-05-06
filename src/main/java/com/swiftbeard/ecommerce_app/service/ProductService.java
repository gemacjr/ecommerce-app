package com.swiftbeard.ecommerce_app.service;

import com.swiftbeard.ecommerce_app.entity.ProductEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.Optional;
import org.springframework.validation.annotation.Validated;


@Validated
public interface ProductService {
    @NotNull Iterable<ProductEntity> getAllProducts();
    Optional<ProductEntity> getProduct(@Min(value = 1L, message = "Invalid product ID.") String id);
}
