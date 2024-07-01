package com.example.todoappdeel3.PromocodeTests;

import com.example.todoappdeel3.dao.PromoCodeDAO;
import com.example.todoappdeel3.dao.PromoCodeRepository;
import com.example.todoappdeel3.dto.PromoCodeDTO;
import com.example.todoappdeel3.models.PromoCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PromocodeDAOTest {

    @Mock
    private PromoCodeRepository promoCodeRepository;

    @InjectMocks
    private PromoCodeDAO promoCodeDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePromoCode_NewCode() {
        PromoCodeDTO promoCodeDTO = new PromoCodeDTO("NEWCODE", 10, "Percentage", 50, LocalDate.of(2024, 12, 31));
        when(promoCodeRepository.findByCode("NEWCODE")).thenReturn(null);

        promoCodeDAO.createPromoCode(promoCodeDTO);

        ArgumentCaptor<PromoCode> promoCodeCaptor = ArgumentCaptor.forClass(PromoCode.class);
        verify(promoCodeRepository).save(promoCodeCaptor.capture());

        PromoCode savedPromoCode = promoCodeCaptor.getValue();
        assertEquals("NEWCODE", savedPromoCode.getCode());
        assertEquals(10, savedPromoCode.getDiscount());
        assertEquals(LocalDate.of(2024, 12, 31), savedPromoCode.getExpiryDate());
        assertEquals("Percentage", savedPromoCode.getType());
        assertEquals(50, savedPromoCode.getMinimumAmount());
    }

    @Test
    public void testCreatePromoCode_ExistingCode() {
        PromoCodeDTO promoCodeDTO = new PromoCodeDTO("EXISTINGCODE", 15, "Amount", 100, LocalDate.of(2025, 1, 1));
        PromoCode existingPromoCode = new PromoCode("EXISTINGCODE", 10, LocalDate.of(2024, 12, 31), "Amount", 50);
        when(promoCodeRepository.findByCode("EXISTINGCODE")).thenReturn(existingPromoCode);

        promoCodeDAO.createPromoCode(promoCodeDTO);

        verify(promoCodeRepository).save(existingPromoCode);
        assertEquals(15, existingPromoCode.getDiscount());
        assertEquals(LocalDate.of(2025, 1, 1), existingPromoCode.getExpiryDate());
        assertEquals("Amount", existingPromoCode.getType());
        assertEquals(100, existingPromoCode.getMinimumAmount());
    }



    @Test
    public void testGetPromoCode() {
        PromoCode promoCode = new PromoCode("CODE123", 10, LocalDate.of(2024, 12, 31), "Percentage", 50);
        when(promoCodeRepository.findByCode("CODE123")).thenReturn(promoCode);

        PromoCode result = promoCodeDAO.getPromoCode("CODE123");

        assertEquals("CODE123", result.getCode());
        assertEquals(10, result.getDiscount());
        assertEquals(LocalDate.of(2024, 12, 31), result.getExpiryDate());
        assertEquals("Percentage", result.getType());
        assertEquals(50, result.getMinimumAmount());
    }

    @Test
    public void testGetAllActivePromoCodes() {
        PromoCode activePromoCode = new PromoCode("ACTIVE", 10, LocalDate.now().plusDays(1), "Percentage", 50);
        PromoCode expiredPromoCode = new PromoCode("EXPIRED", 10, LocalDate.now().minusDays(1), "Percentage", 50);
        List<PromoCode> promoCodes = new ArrayList<>(Arrays.asList(activePromoCode, expiredPromoCode));
        when(promoCodeRepository.findAll()).thenReturn(promoCodes);

        List<PromoCode> result = promoCodeDAO.getAllActivePromoCodes();

        assertEquals(1, result.size());
        assertEquals("ACTIVE", result.get(0).getCode());
    }

    @Test
    public void testGetAllInactivePromoCodes() {
        PromoCode activePromoCode = new PromoCode("ACTIVE", 10, LocalDate.now().plusDays(1), "Percentage", 50);
        PromoCode expiredPromoCode = new PromoCode("EXPIRED", 10, LocalDate.now().minusDays(1), "Percentage", 50);
        List<PromoCode> promoCodes = new ArrayList<>(Arrays.asList(activePromoCode, expiredPromoCode));
        when(promoCodeRepository.findAll()).thenReturn(promoCodes);

        List<PromoCode> result = promoCodeDAO.getAllInactivePromoCodes();

        assertEquals(1, result.size());
        assertEquals("EXPIRED", result.get(0).getCode());
    }

}
