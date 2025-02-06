package com.softwear.berberapp.entity;

import jakarta.persistence.*;
import lombok.Data;

// entities/Hizmet.java
@Entity
@Table(
        name = "hizmetler",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"ad", "berber_id"}, // Aynı berberde aynı hizmet adı olamaz
                name = "unique_hizmet_per_berber"
        )
)
@Data
public class Hizmet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ad;

    @Column(nullable = false)
    private double ucret;

    @ManyToOne
    @JoinColumn(name = "berber_id", nullable = false)
    private Berber berber;
}