package com.example.todoappdeel3.dto;

import java.time.LocalDate;

public class PromoCodeDTO {
    public String code;
    public int discount;
    public LocalDate expiryDate;

    public PromoCodeDTO(String code, int discount, LocalDate expiryDate) {
        this.code = code;
        this.discount = discount;
        this.expiryDate = expiryDate;
    }
}
