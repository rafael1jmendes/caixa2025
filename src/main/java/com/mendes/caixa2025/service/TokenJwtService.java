package com.mendes.caixa2025.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class TokenJwtService {

    @Value("${jwt.secret}") // Chave secreta para assinar o token (definida no application.yml)
    private String secret;

    @Value("${jwt.expiration}") // Tempo de expiração do token em milissegundos (definido no application.yml)
    private long expiration;
    
    public long getExpirationTime() {
        return this.expiration; // Retorna o valor em milissegundos
    }

    // Gera uma chave secreta com base na string "secret"
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // Gera um token JWT para um usuário
    public String gerarToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return criarToken(claims, username);
    }

    // Cria o token JWT com os claims e o subject (username)
    private String criarToken(Map<String, Object> claims, String username) {
        Date agora = new Date();
        Date expiracao = new Date(agora.getTime() + expiration);

        return Jwts.builder()
            .setClaims(claims) // Claims (informações adicionais)
            .setSubject(username) // Subject (normalmente o username)
            .setIssuedAt(agora) // Data de criação
            .setExpiration(expiracao) // Data de expiração
            .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Assinatura
            .compact();
    }

    // Valida um token JWT
    public boolean validarToken(String token, String username) {
        try {
            final String tokenUsername = extrairUsername(token);
            return (tokenUsername.equals(username) && !tokenExpirado(token));
        } catch (Exception e) {
            return false; // Token inválido
        }
    }

    // Extrai o username do token
    public String extrairUsername(String token) {
        return extrairClaim(token, Claims::getSubject);
    }

    // Extrai a data de expiração do token
    public Date extrairExpiracao(String token) {
        return extrairClaim(token, Claims::getExpiration);
    }

    // Extrai um claim específico do token
    private <T> T extrairClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extrairTodosClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extrai todos os claims do token
    private Claims extrairTodosClaims(String token) {
        return Jwts.parserBuilder()
                   .setSigningKey(getSigningKey()) // Define a chave de assinatura
                   .build()
                   .parseClaimsJws(token) // Faz o parsing do token
                   .getBody(); // Retorna os claims (informações) do token
    }

    // Verifica se o token expirou
    private boolean tokenExpirado(String token) {
        return extrairExpiracao(token).before(new Date());
    }

   
}
