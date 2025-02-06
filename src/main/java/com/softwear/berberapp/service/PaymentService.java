package com.softwear.berberapp.service;


import com.softwear.berberapp.dto.PaymentRequest;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public boolean processPayment(PaymentRequest request) {
        // Iyzico veya Papara API'sini çağır
        // Ödeme işlemini gerçekleştir
        return true; // Ödeme başarılı ise true döndür
    }
}