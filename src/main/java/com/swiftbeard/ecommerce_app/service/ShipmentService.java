package com.swiftbeard.ecommerce_app.service;

import com.swiftbeard.ecommerce_app.entity.ShipmentEntity;
import jakarta.validation.constraints.Min;

public interface ShipmentService {
    Iterable<ShipmentEntity> getShipmentByOrderId(@Min(value = 1L, message = "Invalid product ID.")  String id);
}
