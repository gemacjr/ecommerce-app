package com.swiftbeard.ecommerce_app.repository;

import com.swiftbeard.ecommerce_app.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface AddressRepository extends CrudRepository<AddressEntity, UUID> {
}
