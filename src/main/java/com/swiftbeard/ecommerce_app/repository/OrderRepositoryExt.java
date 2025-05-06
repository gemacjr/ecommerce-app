package com.swiftbeard.ecommerce_app.repository;

import com.swiftbeard.ecommerce_app.entity.OrderEntity;
import com.swiftbeard.ecommerce_app.model.NewOrder;

import java.util.Optional;

public interface OrderRepositoryExt {
    Optional<OrderEntity> insert(NewOrder m);
}
