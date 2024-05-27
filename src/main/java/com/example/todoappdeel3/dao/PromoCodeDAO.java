package com.example.todoappdeel3.dao;

import com.example.todoappdeel3.dto.PromoCodeDTO;
import com.example.todoappdeel3.models.PromoCode;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PromoCodeDAO {
    private final PromoCodeRepository promoCodeRepository;

    public PromoCodeDAO(PromoCodeRepository promoCodeRepository) {
        this.promoCodeRepository = promoCodeRepository;
    }

    public void createPromoCode(PromoCodeDTO promoCodeDTO) {
        PromoCode promoCode = new PromoCode(promoCodeDTO.code, promoCodeDTO.discount, promoCodeDTO.expiryDate);
        promoCodeRepository.save(promoCode);
    }

    public List<PromoCode> getAllActivePromoCodes() {
        List<PromoCode> promoCodes = promoCodeRepository.findAll();
        promoCodes.removeIf(promoCode -> promoCode.getExpiryDate().isBefore(java.time.LocalDate.now()));
        return promoCodes;
    }

    public List<PromoCode> getAllInactivePromoCodes() {
        List<PromoCode> promoCodes = promoCodeRepository.findAll();
        promoCodes.removeIf(promoCode -> promoCode.getExpiryDate().isAfter(java.time.LocalDate.now()));
        return promoCodes;
    }
}
