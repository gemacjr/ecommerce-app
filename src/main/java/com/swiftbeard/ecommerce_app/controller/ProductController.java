package com.swiftbeard.ecommerce_app.controller;

import static org.springframework.http.ResponseEntity.ok;

import com.swiftbeard.ecommerce_app.ProductApi;
import com.swiftbeard.ecommerce_app.hateoas.ProductRepresentationModelAssembler;
import com.swiftbeard.ecommerce_app.model.Product;
import com.swiftbeard.ecommerce_app.service.ProductService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter04 - Modern API Development with Spring and Spring Boot Ed 2
 **/
@RestController
public class ProductController implements ProductApi {

    private final ProductService service;
    private final ProductRepresentationModelAssembler assembler;

    public ProductController(ProductService service, ProductRepresentationModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @Override
    public ResponseEntity<Product> getProduct(String id) {
        return service.getProduct(id).map(assembler::toModel).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<Product>> queryProducts(@Valid String tag, @Valid String name,
                                                       @Valid Integer page, @Valid Integer size) {
        return ok(assembler.toListModel(service.getAllProducts()));
    }
}
