package com.mendes.caixa2025;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mendes.caixa2025.controller.AutenticacaoController;
import com.mendes.caixa2025.model.LoginRequest;
import com.mendes.caixa2025.service.AuthService;

@WebMvcTest(AutenticacaoController.class)
public class AutenticacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("unused")
    @Autowired
    private AuthService authService; // Usando uma instância real do AuthService

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration // Configuração de teste para fornecer uma instância real do AuthService
    static class TestConfig {
        @Bean
        public AuthService authService() {
            return new AuthService(null); // Instância real do AuthService
        }
    }

    @BeforeEach
    public void setUp() {
        // Configurações iniciais, se necessário
    }

    @Test
    public void testLogin_Sucesso() throws Exception {
        // Configuração do comportamento esperado (sem Mockito)
        // Aqui você pode configurar o AuthService para retornar o valor desejado
        // Exemplo: authService.setToken("token_gerado");

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("senha123");

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("token_gerado"));
    }

    @Test
    public void testLogin_CredenciaisInvalidas() throws Exception {
        // Configuração do comportamento esperado (sem Mockito)
        // Aqui você pode configurar o AuthService para lançar uma exceção
        // Exemplo: authService.setThrowException(true);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("senha_errada");

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isInternalServerError());
    }
}