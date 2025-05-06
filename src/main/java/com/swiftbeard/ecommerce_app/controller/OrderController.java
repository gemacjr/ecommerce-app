package com.swiftbeard.ecommerce_app.controller;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

import com.swiftbeard.ecommerce_app.OrderApi;
import com.swiftbeard.ecommerce_app.hateoas.OrderRepresentationModelAssembler;
import com.swiftbeard.ecommerce_app.model.NewOrder;
import com.swiftbeard.ecommerce_app.model.Order;
import com.swiftbeard.ecommerce_app.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController implements OrderApi {

    private final OrderRepresentationModelAssembler assembler;
    private final OrderService service;

    public OrderController(OrderService service, OrderRepresentationModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @Override
    public ResponseEntity<Order> addOrder(@Valid NewOrder newOrder) {
        return service.addOrder(newOrder).map(assembler::toModel).map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

    @Override
    public ResponseEntity<List<Order>> getOrdersByCustomerId(@NotNull @Valid String customerId) {
        return ok(assembler.toListModel(service.getOrdersByCustomerId(customerId)));
    }

    @Override
    public ResponseEntity<Order> getByOrderId(String id) {
        return service.getByOrderId(id).map(assembler::toModel).map(ResponseEntity::ok)
                .orElse(notFound().build());
    }
}
