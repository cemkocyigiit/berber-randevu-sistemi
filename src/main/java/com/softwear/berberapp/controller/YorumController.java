package com.softwear.berberapp.controller;


import com.softwear.berberapp.dto.YorumRequest;
import com.softwear.berberapp.entity.Berber;
import com.softwear.berberapp.entity.User;
import com.softwear.berberapp.entity.Yorum;
import com.softwear.berberapp.repository.BerberRepository;
import com.softwear.berberapp.repository.UserRepository;
import com.softwear.berberapp.repository.YorumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/yorum")
@RequiredArgsConstructor
public class YorumController {

    private final YorumRepository yorumRepository;
    private final UserRepository userRepository;
    private final BerberRepository berberRepository;

    @PostMapping("/ekle")
    public ResponseEntity<?> yorumEkle(@RequestBody YorumRequest request) {
        // Giriş yapan kullanıcıyı al
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User kullanici = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı!"));

        // Berberi bul
        Berber berber = berberRepository.findById(request.getBerberId())
                .orElseThrow(() -> new RuntimeException("Berber bulunamadı!"));

        // Yorumu oluştur ve kaydet
        Yorum yorum = new Yorum();
        yorum.setKullanici(kullanici);
        yorum.setBerber(berber);
        yorum.setYorumMetni(request.getYorumMetni());
        yorum.setPuan(request.getPuan());
        yorumRepository.save(yorum);

        return ResponseEntity.ok("Yorum başarıyla eklendi!");
    }

    @GetMapping("/berber/{berberId}")
    public List<Yorum> berberYorumlari(@PathVariable Long berberId) {
        return yorumRepository.findByBerberId(berberId);
    }

    @GetMapping("/berber/{berberId}/ortalama-puan")
    public ResponseEntity<Double> getOrtalamaPuan(@PathVariable Long berberId) {
        List<Yorum> yorumlar = yorumRepository.findByBerberId(berberId);

        if (yorumlar.isEmpty()) {
            return ResponseEntity.ok(0.0);
        }

        double ortalama = yorumlar.stream()
                .mapToInt(Yorum::getPuan)
                .average()
                .orElse(0.0);

        return ResponseEntity.ok(ortalama);
    }
}