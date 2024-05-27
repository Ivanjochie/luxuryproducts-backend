package com.example.todoappdeel3.controller;

import com.example.todoappdeel3.dao.PromoCodeDAO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/create")
public class PromoCodeController {
    private final PromoCodeDAO promoCodeDAO;

    public PromoCodeController(PromoCodeDAO promoCodeDAO) {
        this.promoCodeDAO = promoCodeDAO;
    }


}
