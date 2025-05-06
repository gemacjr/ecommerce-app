package com.swiftbeard.ecommerce_app.service;

import com.swiftbeard.ecommerce_app.entity.OrderEntity;
import com.swiftbeard.ecommerce_app.model.NewOrder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.Optional;

public interface OrderService {

    Optional<OrderEntity> addOrder(@Valid NewOrder newOrder);
    Iterable<OrderEntity> getOrdersByCustomerId(@NotNull @Valid String customerId);
    Optional<OrderEntity> getByOrderId(String id);
}
