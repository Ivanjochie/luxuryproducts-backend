package com.example.todoappdeel3.controller;

import com.example.todoappdeel3.dao.PromoCodeDAO;
import com.example.todoappdeel3.dto.PromoCodeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/create")
public class PromoCodeController {
    private final PromoCodeDAO promoCodeDAO;

    public PromoCodeController(PromoCodeDAO promoCodeDAO) {
        this.promoCodeDAO = promoCodeDAO;
    }

    @PostMapping
    public ResponseEntity<String> createPromoCode(@RequestBody PromoCodeDTO promoCodeDTO) {
        this.promoCodeDAO.createPromoCode(promoCodeDTO);
        return ResponseEntity.ok("Promo code created");
    }


}
