package com.mendes.caixa2025.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProtectEP {

    @GetMapping("/protegido")
    public String endpointProtegido() {
        return "Acesso permitido!";
    }
}