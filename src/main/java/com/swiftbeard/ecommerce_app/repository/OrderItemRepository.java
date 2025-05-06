package com.swiftbeard.ecommerce_app.repository;

import java.util.UUID;

import com.swiftbeard.ecommerce_app.entity.OrderItemEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItemEntity, UUID> {
}
