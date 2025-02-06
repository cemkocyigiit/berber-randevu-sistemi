package com.softwear.berberapp.controller;


import com.softwear.berberapp.dto.RandevuRequest;
import com.softwear.berberapp.entity.Randevu;
import com.softwear.berberapp.repository.BerberRepository;
import com.softwear.berberapp.repository.RandevuRepository;
import com.softwear.berberapp.repository.UserRepository;
import com.softwear.berberapp.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;

@RestController
@RequestMapping("/api/randevu")
@RequiredArgsConstructor
public class RandevuController {

    private final RandevuRepository randevuRepository;
    private final BerberRepository berberRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    @PostMapping("/al")
    public ResponseEntity<?> randevuAl(@RequestBody RandevuRequest request) {
        // Randevu kaydetme işlemi
        Randevu randevu = new Randevu();
        randevu.setBerber(berberRepository.findById(request.getBerberId()).orElseThrow());
        randevu.setCustomer(userRepository.findById(request.getCustomerId()).orElseThrow());
        randevu.setTarih(request.getTarih());
        randevuRepository.save(randevu);

        // E-posta gönder
        emailService.sendEmail(
                randevu.getCustomer().getUsername(),
                "Randevu Onayı",
                "Randevunuz başarıyla alındı. Tarih: " + randevu.getTarih()
        );

        return ResponseEntity.ok("Randevu alındı!");
    }


    @DeleteMapping("/iptal/{id}")
    public ResponseEntity<?> randevuIptal(@PathVariable Long id) {
        Randevu randevu = randevuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Böyle bir randevu zaten bulunmuyor."));

        randevuRepository.delete(randevu);

        return ResponseEntity.ok("Randevu iptal edildi!");
    }

    @PutMapping("/guncelle/{id}")
    public ResponseEntity<?> randevuGuncelle(@PathVariable Long id, @RequestBody LocalDateTime yeniTarih) {
        Randevu randevu = randevuRepository.findById(id)
                .orElseThrow();
        randevu.setTarih(yeniTarih);
        randevuRepository.save(randevu);
        return ResponseEntity.ok("Randevu güncellendi!");
    }
}