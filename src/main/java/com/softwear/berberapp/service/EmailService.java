package com.softwear.berberapp.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendEmail(String to, String subject, String text) {
        // E-posta gönderme işlemi
        System.out.println("E-posta gönderildi: " + to + " - " + subject);
    }
}