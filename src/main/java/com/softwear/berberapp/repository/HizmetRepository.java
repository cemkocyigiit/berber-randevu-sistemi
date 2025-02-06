package com.softwear.berberapp.repository;

import com.softwear.berberapp.entity.Hizmet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HizmetRepository extends JpaRepository<Hizmet, Long> {
    // Berber ID'sine göre hizmetleri döndüren metod
    List<Hizmet> findByBerberId(Long berberId);
    boolean existsByAdIgnoreCaseAndBerberId(String ad, Long berberId);

}
