package com.softwear.berberapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "yorumlar")
public class Yorum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "kullanici_id", nullable = false)
    private User kullanici;

    @ManyToOne
    @JoinColumn(name = "berber_id", nullable = false)
    private Berber berber;

    @Column(columnDefinition = "TEXT")
    private String yorumMetni;

    private int puan; // 1-5 arası

    private LocalDateTime tarih = LocalDateTime.now();
}