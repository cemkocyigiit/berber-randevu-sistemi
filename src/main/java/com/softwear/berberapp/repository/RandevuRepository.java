package com.softwear.berberapp.repository;
import com.softwear.berberapp.entity.Randevu;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface RandevuRepository extends JpaRepository<Randevu, Long> {
    // Bu metodu ekleyin
    boolean existsByBerberIdAndTarih(Long berberId, LocalDateTime tarih);
}