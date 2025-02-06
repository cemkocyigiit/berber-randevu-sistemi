package com.softwear.berberapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Salon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(name = "working_hours")
    private String workingHours;

}