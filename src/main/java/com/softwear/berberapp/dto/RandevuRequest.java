package com.softwear.berberapp.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RandevuRequest {
    private Long berberId;
    private Long customerId;
    private LocalDateTime tarih;
}