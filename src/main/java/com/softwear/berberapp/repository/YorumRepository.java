package com.softwear.berberapp.repository;

import com.softwear.berberapp.entity.Yorum;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface YorumRepository extends JpaRepository<Yorum, Long> {
    List<Yorum> findByBerberId(Long berberId);
}