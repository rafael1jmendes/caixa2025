package com.mendes.caixa2025.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data                   // Gera getters, setters, toString, equals, hashCode //
@NoArgsConstructor      // Gera construtor sem argumentos // 
@AllArgsConstructor     // Gera construtor com todos os argumentos // 
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private boolean enabled;
}