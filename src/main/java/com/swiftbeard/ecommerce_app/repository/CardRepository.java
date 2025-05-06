package com.swiftbeard.ecommerce_app.repository;

import java.util.UUID;

import com.swiftbeard.ecommerce_app.entity.CardEntity;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<CardEntity, UUID> {
}
