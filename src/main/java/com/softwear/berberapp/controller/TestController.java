package com.softwear.berberapp.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/merhaba")
    public String merhaba() {
        return "Merhaba, berber uygulaması çalışıyor!";
    }
}