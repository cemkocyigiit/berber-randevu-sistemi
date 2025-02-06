package com.softwear.berberapp.controller;


import com.softwear.berberapp.entity.Berber;
import com.softwear.berberapp.repository.BerberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/berber")
@RequiredArgsConstructor
public class BerberController {

    private final BerberRepository berberRepository;

    @GetMapping("/listele")
    public List<Berber> tumBerberleriListele() {
        return berberRepository.findAll();
    }

    @GetMapping("/detay/{username}")
    public ResponseEntity<Berber> berberDetay(@PathVariable String username) {
        Berber berber = berberRepository.findByUser_Username(username)
                .orElseThrow(() -> new RuntimeException("Berber bulunamadı!"));
        return ResponseEntity.ok(berber);
    }

}