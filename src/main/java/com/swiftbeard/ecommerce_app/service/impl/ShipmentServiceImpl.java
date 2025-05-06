package com.swiftbeard.ecommerce_app.service.impl;

import com.swiftbeard.ecommerce_app.entity.ShipmentEntity;
import com.swiftbeard.ecommerce_app.repository.ShipmentRepository;
import com.swiftbeard.ecommerce_app.service.ShipmentService;
import jakarta.validation.constraints.Min;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;


@Service
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository repository;

    public ShipmentServiceImpl(ShipmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<ShipmentEntity> getShipmentByOrderId(
            @Min(value = 1L, message = "Invalid shipment ID.") String id) {
        return repository.findAllById(List.of(UUID.fromString(id)));
    }
}
