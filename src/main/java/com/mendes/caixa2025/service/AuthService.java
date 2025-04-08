package com.mendes.caixa2025.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthService {

    private final TokenJwtService tokenJwtService;

    public AuthService(TokenJwtService tokenJwtService) {
        this.tokenJwtService = tokenJwtService;
    }

    public String autenticar(String username, String password) {
        // autenticação
        if ("admin".equals(username) && "senha123".equals(password)) {
            String token = tokenJwtService.gerarToken(username);
            if (token == null) throw new RuntimeException("Erro ao gerar token");
            ObjectMapper mapper = new ObjectMapper();
            HashMap<String, Object> response = new HashMap<>();
            response.put("access_token", token);
            response.put("token_type", "Bearer");
            try {
                return mapper.writeValueAsString(response);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Erro ao converter token para JSON", e);
            }
        } else {
            throw new RuntimeException("Credenciais inválidas");
        }
    }
}