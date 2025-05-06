package com.swiftbeard.ecommerce_app.service.impl;

import java.util.Optional;
import java.util.UUID;

import com.swiftbeard.ecommerce_app.entity.AddressEntity;
import com.swiftbeard.ecommerce_app.entity.CardEntity;
import com.swiftbeard.ecommerce_app.entity.UserEntity;
import com.swiftbeard.ecommerce_app.repository.UserRepository;
import com.swiftbeard.ecommerce_app.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteCustomerById(String id) {
        repository.deleteById(UUID.fromString(id));
    }

    @Override
    public Optional<Iterable<AddressEntity>> getAddressesByCustomerId(String id) {
        return repository.findById(UUID.fromString(id)).map(UserEntity::getAddresses);
    }

    @Override
    public Iterable<UserEntity> getAllCustomers() {
        return repository.findAll();
    }

    @Override
    public Optional<CardEntity> getCardByCustomerId(String id) {
        return Optional.of(repository.findById(UUID.fromString(id)).map(UserEntity::getCard).get().get(0));
    }

    @Override
    public Optional<UserEntity> getCustomerById(String id) {
        return repository.findById(UUID.fromString(id));
    }
}
