package com.swiftbeard.ecommerce_app.repository;

import java.util.UUID;

import com.swiftbeard.ecommerce_app.entity.AuthorizationEntity;
import org.springframework.data.repository.CrudRepository;

public interface AuthorizationRepository extends CrudRepository<AuthorizationEntity, UUID> {
}
