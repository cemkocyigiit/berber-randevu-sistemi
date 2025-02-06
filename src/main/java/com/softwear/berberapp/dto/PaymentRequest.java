package com.softwear.berberapp.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private Long randevuId;
    private String cardNumber;
    private String cardHolderName;
    private String expirationDate;
    private String cvv;
}