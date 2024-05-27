package com.example.todoappdeel3.controller;

import com.example.todoappdeel3.dao.PromoCodeDAO;
import com.example.todoappdeel3.dto.PromoCodeDTO;
import com.example.todoappdeel3.dto.PromoCodeUseDTO;
import com.example.todoappdeel3.services.PromoCodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/create")
public class PromoCodeController {
    private final PromoCodeDAO promoCodeDAO;

    private final PromoCodeService promoCodeService;


    public PromoCodeController(PromoCodeDAO promoCodeDAO, PromoCodeService promoCodeService) {
        this.promoCodeDAO = promoCodeDAO;
        this.promoCodeService = promoCodeService;
    }

    @PostMapping
    public ResponseEntity<String> createPromoCode(@RequestBody PromoCodeDTO promoCodeDTO) {
        this.promoCodeDAO.createPromoCode(promoCodeDTO);
        return ResponseEntity.ok("Promo code created");
    }

    @PostMapping("/use")
    public ResponseEntity<String> usePromoCode(@RequestBody PromoCodeUseDTO promoCodeUseDTO) {
        this.promoCodeService.usePromoCode(promoCodeUseDTO.code);
        return ResponseEntity.ok("Promo code used");
    }
}
