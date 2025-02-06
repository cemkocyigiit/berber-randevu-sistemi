package com.softwear.berberapp.dto;

import lombok.Data;

@Data
public class YorumRequest {
    private Long berberId;
    private String yorumMetni;
    private int puan;
}