package com.swiftbeard.ecommerce_app.service;

import com.swiftbeard.ecommerce_app.entity.AddressEntity;
import com.swiftbeard.ecommerce_app.entity.CardEntity;
import com.swiftbeard.ecommerce_app.entity.UserEntity;

import java.util.Optional;

public interface UserService {
    void deleteCustomerById(String id);
    Optional<Iterable<AddressEntity>> getAddressesByCustomerId(String id);
    Iterable<UserEntity> getAllCustomers();
    Optional<CardEntity> getCardByCustomerId(String id);
    Optional<UserEntity> getCustomerById(String id);
}
