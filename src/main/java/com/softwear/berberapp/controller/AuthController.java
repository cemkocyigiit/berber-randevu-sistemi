package com.softwear.berberapp.controller;

import com.softwear.berberapp.entity.User;
import com.softwear.berberapp.repository.BerberRepository;
import com.softwear.berberapp.repository.UserRepository;
import com.softwear.berberapp.service.EmailService;
import com.softwear.berberapp.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, BerberRepository berberRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }





    //------------------------------------------------------------- KAYIT





    @PostMapping("/kayit")
    public ResponseEntity<Map<String, String>> kayit(@RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Collections.singletonMap("hata", "Bu kullanıcı adı zaten alınmış!"));
        }

        user.setRole(user.getRole() != null ?  user.getRole() : "CUSTOMER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap("mesaj", "Kullanıcı kaydedildi!"));
    }







    //------------------------------------------------------------- GİRİŞ








    @PostMapping("/giris")
    public ResponseEntity<Map<String, String>> giris(@RequestBody User user) {
        User dbUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        if (passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            String token = jwtUtil.generateToken(dbUser);
            return ResponseEntity.ok(Collections.singletonMap("token", token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("hata", "Şifre hatalı"));
        }
    }




    //------------------------------------------------------------- ŞİFRE SIFIRLAMA







    EmailService emailService;
    private String generateTempPassword() {
        return String.valueOf((int) (Math.random() * 900000) + 100000);
    }


    @PostMapping("/sifre-sifirla")
    public ResponseEntity<?> sifreSifirla(@RequestParam String email) {
       User user = userRepository.findByUsername(email)
               .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

       // Geçici şifre oluştur ve e-posta gönder
        String tempPassword = generateTempPassword();
        user.setPassword(passwordEncoder.encode(tempPassword));
        userRepository.save(user);

        emailService.sendEmail(email, "Şifre Sıfırlama", "Yeni şifreniz: " + tempPassword);
        return ResponseEntity.ok("Şifre sıfırlama e-postası gönderildi!");
    }
}