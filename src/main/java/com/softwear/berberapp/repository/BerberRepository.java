package com.softwear.berberapp.repository;

import com.softwear.berberapp.entity.Berber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BerberRepository extends JpaRepository<Berber, Long> {
    Optional<Berber> findById(Long id); // Fix: Changed from findByBerberId to findById
    Optional<Berber> findByUser_Username(String username);
}