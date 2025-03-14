package com.mendes.caixa2025.service;

public class AuthService {

    private final TokenJwtService tokenJwtService;

    public AuthService(TokenJwtService tokenJwtService) {
        this.tokenJwtService = tokenJwtService;
    }

    public String autenticar(String username, String password) {
        // autenticação
        if ("admin".equals(username) && "senha123".equals(password)) {
            return tokenJwtService.gerarToken(username);
        } else {
            throw new RuntimeException("Credenciais inválidas");
        }
    }
}