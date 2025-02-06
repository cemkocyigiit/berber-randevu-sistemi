package com.softwear.berberapp.entity;

import com.softwear.berberapp.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

// Berber.java
@Entity
@Data
public class Berber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(name = "working_hours")
    private String workingHours;

    // Yeni eklenen alanlar
    @ElementCollection
    @CollectionTable(name = "berber_hizmetler", joinColumns = @JoinColumn(name = "berber_id"))
    @Column(name = "hizmet")
    private List<String> hizmetler = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "berber_ucretler", joinColumns = @JoinColumn(name = "berber_id"))
    @Column(name = "ucret")
    private List<Double> ucretler = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
