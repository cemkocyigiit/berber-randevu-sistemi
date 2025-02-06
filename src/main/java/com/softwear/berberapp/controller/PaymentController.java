package com.softwear.berberapp.controller;


import com.softwear.berberapp.dto.PaymentRequest;
import com.softwear.berberapp.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/odeme-yap")
    public ResponseEntity<?> odemeYap(@RequestBody PaymentRequest request) {
        boolean result = paymentService.processPayment(request);
        if (result) {
            return ResponseEntity.ok("Ödeme başarılı!");
        } else {
            return ResponseEntity.badRequest().body("Ödeme başarısız!");
        }
    }
}