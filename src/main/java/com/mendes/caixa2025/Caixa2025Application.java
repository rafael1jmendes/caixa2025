package com.mendes.caixa2025;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.mendes.caixa2025.model") // Escaneia o pacote das entidades
public class Caixa2025Application {

    public static void main(String[] args) {
        SpringApplication.run(Caixa2025Application.class, args);
    }
}