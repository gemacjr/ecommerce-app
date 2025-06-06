package com.swiftbeard.ecommerce_app.controller;

import static org.springframework.http.ResponseEntity.accepted;
import static org.springframework.http.ResponseEntity.ok;

import com.swiftbeard.ecommerce_app.CartApi;
import com.swiftbeard.ecommerce_app.hateoas.CartRepresentationModelAssembler;
import com.swiftbeard.ecommerce_app.model.Cart;
import com.swiftbeard.ecommerce_app.model.Item;
import com.swiftbeard.ecommerce_app.service.CartService;
import jakarta.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartsController implements CartApi {

    private static final Logger log = LoggerFactory.getLogger(CartsController.class);
    private final CartService service;
    private final CartRepresentationModelAssembler assembler;

    public CartsController(CartService service, CartRepresentationModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @Override
    public ResponseEntity<List<Item>> addCartItemsByCustomerId(String customerId, @Valid Item item) {
        log.info("Request for customer ID: {}\nItem: {}", customerId, item);
        return ok(service.addCartItemsByCustomerId(customerId, item));
    }

    @Override
    public ResponseEntity<List<Item>> addOrReplaceItemsByCustomerId(String customerId,
                                                                    @Valid Item item) {
        return ok(service.addOrReplaceItemsByCustomerId(customerId, item));
    }

    @Override
    public ResponseEntity<Void> deleteCart(String customerId) {
        service.deleteCart(customerId);
        return accepted().build();
    }

    @Override
    public ResponseEntity<Void> deleteItemFromCart(String customerId, String itemId) {
        service.deleteItemFromCart(customerId, itemId);
        return accepted().build();
    }

    @Override
    public ResponseEntity<Cart> getCartByCustomerId(String customerId) {
        return ok(assembler.toModel(service.getCartByCustomerId(customerId)));
    }

    @Override
    public ResponseEntity<List<Item>> getCartItemsByCustomerId(String customerId) {
        return ok(service.getCartItemsByCustomerId(customerId));
    }

    @Override
    public ResponseEntity<Item> getCartItemsByItemId(String customerId, String itemId) {
        return ok(service.getCartItemsByItemId(customerId, itemId));
    }
}
