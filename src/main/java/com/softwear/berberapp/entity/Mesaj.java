package com.softwear.berberapp.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Mesaj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User gonderen;

    @ManyToOne
    private User alici;

    private String mesajIcerigi;
    private LocalDateTime tarih;
}