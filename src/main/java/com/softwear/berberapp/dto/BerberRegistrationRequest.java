package com.softwear.berberapp.dto;

import lombok.Data;

@Data
public class BerberRegistrationRequest {
    private String email;    // Kullanıcı adı olarak kullanılacak
    private String password;
    private String name;     // Berber adı
    private String location; // Konum bilgisi
    private String workingHours; // Çalışma saatleri
}