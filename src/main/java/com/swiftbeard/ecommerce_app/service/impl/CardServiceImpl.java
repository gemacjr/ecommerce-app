package com.swiftbeard.ecommerce_app.service.impl;

import com.swiftbeard.ecommerce_app.entity.CardEntity;
import com.swiftbeard.ecommerce_app.entity.UserEntity;
import com.swiftbeard.ecommerce_app.model.AddCardReq;
import com.swiftbeard.ecommerce_app.repository.CardRepository;
import com.swiftbeard.ecommerce_app.repository.UserRepository;
import com.swiftbeard.ecommerce_app.service.CardService;
import jakarta.validation.Valid;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter04 - Modern API Development with Spring and Spring Boot Ed 2
 **/
@SuppressWarnings("unchecked")
@Service
public class CardServiceImpl implements CardService {
    private final CardRepository repository;
    private final UserRepository userRepo;

    public CardServiceImpl(CardRepository repository, UserRepository userRepo) {
        this.repository = repository;
        this.userRepo = userRepo;
    }

    @Override
    public void deleteCardById(String id) {
        repository.deleteById(UUID.fromString(id));
    }

    @Override
    public Iterable<CardEntity> getAllCards() {
        Iterable<CardEntity> all = repository.findAll();
        return all;
    }

    @Override
    public Optional<CardEntity> getCardById(String id) {
        return repository.findById(UUID.fromString(id));
    }

    @Override
    public Optional<CardEntity> registerCard(@Valid AddCardReq addCardReq) {
        // add validation to make sure that only single card exists from one user
        // else it throws DataIntegrityViolationException for user_id (unique)
        return Optional.of(repository.save(toEntity(addCardReq)));
    }

    private CardEntity toEntity(AddCardReq m) {
        CardEntity e = new CardEntity();
        Optional<UserEntity> user = userRepo.findById(UUID.fromString(m.getUserId()));
        user.ifPresent(e::setUser);
        return e.setNumber(m.getCardNumber()).setCvv(m.getCvv())
                .setExpires(m.getExpires());
    }
}
