package com.softwear.berberapp.controller;

import com.softwear.berberapp.dto.BerberRegistrationRequest;
import com.softwear.berberapp.entity.Salon;
import com.softwear.berberapp.repository.BerberRepository;
import com.softwear.berberapp.repository.SalonRepository;
import com.softwear.berberapp.repository.UserRepository;
import com.softwear.berberapp.util.JwtUtil;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.softwear.berberapp.entity.User;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserRepository userRepository;
    private final SalonRepository salonRepository;


    public AdminController(UserRepository userRepository, SalonRepository salonRepository) {
        this.salonRepository= salonRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/kullanici-listesi")
    public List<User> tumKullanicilariGetir() {
        return userRepository.findAll();
    }

    @PostMapping("/salon-kayit")
    public ResponseEntity<?> berberKayit(@RequestBody BerberRegistrationRequest request) {
        // Berber detaylarını kaydet
        Salon salon = new Salon();
        salon.setName(request.getName());
        salon.setLocation(request.getLocation());
        salon.setWorkingHours(request.getWorkingHours());
        salonRepository.save(salon);

        return ResponseEntity.ok("Salon kaydedildi!");
    }
}