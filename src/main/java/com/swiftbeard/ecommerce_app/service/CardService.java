package com.swiftbeard.ecommerce_app.service;

import com.swiftbeard.ecommerce_app.entity.CardEntity;
import com.swiftbeard.ecommerce_app.model.AddCardReq;
import jakarta.validation.Valid;
import java.util.Optional;

public interface CardService {
    void deleteCardById(String id);
    Iterable<CardEntity> getAllCards();
    Optional<CardEntity> getCardById(String id);
    Optional<CardEntity> registerCard(@Valid AddCardReq addCardReq);
}