package com.softwear.berberapp.controller;


import com.softwear.berberapp.dto.HizmetRequest;
import com.softwear.berberapp.entity.Berber;
import com.softwear.berberapp.entity.Hizmet;
import com.softwear.berberapp.repository.BerberRepository;
import com.softwear.berberapp.repository.HizmetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// controllers/HizmetController.java
@RestController
@RequestMapping("/api/hizmet")
public class HizmetController {

    private final HizmetRepository hizmetRepository;
    private final BerberRepository berberRepository;

    public HizmetController(HizmetRepository hizmetRepository, BerberRepository berberRepository) {
        this.hizmetRepository = hizmetRepository;
        this.berberRepository = berberRepository;
    }

    @PostMapping("/ekle/{berberId}")
    public ResponseEntity<?> hizmetEkle(
            @PathVariable Long berberId,
            @RequestBody HizmetRequest request
    ) {
        // Aynı isimde hizmet var mı kontrol et (Case-insensitive)
        boolean hizmetVarMi = hizmetRepository.existsByAdIgnoreCaseAndBerberId(
                request.getAd().trim(), // Boşlukları temizle
                berberId
        );

        if (hizmetVarMi) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Bu hizmet zaten eklenmiş!");
        }

        // Yeni hizmeti kaydet
        Berber berber = berberRepository.findById(berberId)
                .orElseThrow(() -> new RuntimeException("Berber bulunamadı!"));

        Hizmet hizmet = new Hizmet();
        hizmet.setAd(request.getAd().trim()); // Başta/sondaki boşlukları kaldır
        hizmet.setUcret(request.getUcret());
        hizmet.setBerber(berber);

        hizmetRepository.save(hizmet);

        return ResponseEntity.ok("Hizmet başarıyla eklendi!");
    }

    @PutMapping("/guncelle/{hizmetId}")
    public ResponseEntity<?> hizmetGuncelle(
            @PathVariable Long hizmetId,
            @RequestBody HizmetRequest request
    ) {
        Hizmet hizmet = hizmetRepository.findById(hizmetId)
                .orElseThrow(() -> new RuntimeException("Hizmet bulunamadı!"));

        hizmet.setAd(request.getAd());
        hizmet.setUcret(request.getUcret());
        hizmetRepository.save(hizmet);

        return ResponseEntity.ok("Hizmet güncellendi!");
    }

    @DeleteMapping("/sil/{hizmetId}")
    public ResponseEntity<?> hizmetSil(@PathVariable Long hizmetId) {
        hizmetRepository.deleteById(hizmetId);
        return ResponseEntity.ok("Hizmet silindi!");
    }

    @GetMapping("/berber/{berberId}")
    public List<Hizmet> berberHizmetleri(@PathVariable Long berberId) {
        return hizmetRepository.findByBerberId(berberId);
    }
}