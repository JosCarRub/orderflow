package com.josecarlosdev.orderflow.infrastructure.web.controller;

import com.josecarlosdev.orderflow.application.usecase.CreateCheckoutUseCase;
import com.josecarlosdev.orderflow.application.usecase.result.CheckoutResult;
import com.josecarlosdev.orderflow.infrastructure.web.ApiPaths;
import com.josecarlosdev.orderflow.infrastructure.web.dto.CheckoutRequest;
import com.josecarlosdev.orderflow.infrastructure.web.dto.CheckoutResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckoutController {

    private final CreateCheckoutUseCase createCheckout;

    public CheckoutController(CreateCheckoutUseCase createCheckout) {
        this.createCheckout = createCheckout;
    }

    @PostMapping(ApiPaths.CHECKOUT)
    public ResponseEntity<CheckoutResponse> checkout(@Valid @RequestBody CheckoutRequest request){
        CheckoutResult result = createCheckout.handleOrder(request.productId(), request.quantity());
        return ResponseEntity.status(HttpStatus.CREATED).body(CheckoutResponse.from(result));
    }
}
