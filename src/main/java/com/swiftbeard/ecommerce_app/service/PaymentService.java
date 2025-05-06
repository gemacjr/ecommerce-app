package com.swiftbeard.ecommerce_app.service;

import com.swiftbeard.ecommerce_app.entity.AuthorizationEntity;
import com.swiftbeard.ecommerce_app.model.PaymentReq;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.Optional;

public interface PaymentService {

    Optional<AuthorizationEntity> authorize(@Valid PaymentReq paymentReq);
    Optional<AuthorizationEntity> getOrdersPaymentAuthorization(@NotNull String orderId);
}
