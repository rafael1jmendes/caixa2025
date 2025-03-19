package com.mendes.caixa2025;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.mendes.caixa2025.controller.AutenticacaoController;
import com.mendes.caixa2025.model.LoginRequest;
import org.springframework.test.web.servlet.ResultActions;
//import com.mendes.caixa2025.service.AuthService;
//import com.mendes.caixa2025.service.TokenJwtService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc // Configura o MockMvc automaticamente no contexto completo
public class AutenticacaoControllerTest {

    final String urlAPI = "http://localhost:5678";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLogin_Sucesso() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("senha123");

        ResultActions act = mockMvc.perform(post(urlAPI + "/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"access_token\":\"token\",\"token_type\":\"Bearer\"}")); // Aqui precisa bater com o retorno real

        System.out.println("TOKEN: " + act.andReturn().getResponse().getContentAsString());
    }

    @Test
    public void testLogin_CredenciaisInvalidas() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("senha_errada");

        mockMvc.perform(post( urlAPI + "/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized()); // Ajuste de acordo com o comportamento real
    }
}
