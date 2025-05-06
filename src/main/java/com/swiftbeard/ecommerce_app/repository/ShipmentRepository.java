package com.swiftbeard.ecommerce_app.repository;

import java.util.UUID;

import com.swiftbeard.ecommerce_app.entity.ShipmentEntity;
import org.springframework.data.repository.CrudRepository;

public interface ShipmentRepository extends CrudRepository<ShipmentEntity, UUID> {
}
