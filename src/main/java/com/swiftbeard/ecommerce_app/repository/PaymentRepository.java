package com.swiftbeard.ecommerce_app.repository;

import com.swiftbeard.ecommerce_app.entity.PaymentEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface PaymentRepository extends CrudRepository<PaymentEntity, UUID> {
}
