package com.swiftbeard.ecommerce_app.controller;

import static org.springframework.http.ResponseEntity.accepted;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

import java.util.List;

import com.swiftbeard.ecommerce_app.CustomerApi;
import com.swiftbeard.ecommerce_app.hateoas.AddressRepresentationModelAssembler;
import com.swiftbeard.ecommerce_app.hateoas.CardRepresentationModelAssembler;
import com.swiftbeard.ecommerce_app.hateoas.UserRepresentationModelAssembler;
import com.swiftbeard.ecommerce_app.model.Address;
import com.swiftbeard.ecommerce_app.model.Card;
import com.swiftbeard.ecommerce_app.model.User;
import com.swiftbeard.ecommerce_app.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController implements CustomerApi {

    private final UserRepresentationModelAssembler assembler;
    private final AddressRepresentationModelAssembler addrAssembler;
    private final CardRepresentationModelAssembler cardAssembler;
    private final UserService service;

    public CustomerController(UserService service, UserRepresentationModelAssembler assembler,
                              AddressRepresentationModelAssembler addrAssembler,
                              CardRepresentationModelAssembler cardAssembler) {
        this.service = service;
        this.assembler = assembler;
        this.addrAssembler = addrAssembler;
        this.cardAssembler = cardAssembler;
    }

    @Override
    public ResponseEntity<Void> deleteCustomerById(String id) {
        service.deleteCustomerById(id);
        return accepted().build();
    }

    @Override
    public ResponseEntity<List<Address>> getAddressesByCustomerId(String id) {
        return service.getAddressesByCustomerId(id).map(addrAssembler::toListModel)
                .map(ResponseEntity::ok).orElse(notFound().build());
    }

    @Override
    public ResponseEntity<List<User>> getAllCustomers() {
        return ok(assembler.toListModel(service.getAllCustomers()));
    }

    @Override
    public ResponseEntity<Card> getCardByCustomerId(String id) {
        return service.getCardByCustomerId(id).map(cardAssembler::toModel).map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

    @Override
    public ResponseEntity<User> getCustomerById(String id) {
        return service.getCustomerById(id).map(assembler::toModel).map(ResponseEntity::ok)
                .orElse(notFound().build());
    }
}
