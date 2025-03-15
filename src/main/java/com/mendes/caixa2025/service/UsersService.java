package com.mendes.caixa2025.service;

import com.mendes.caixa2025.model.Users;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    public void criarUsuario(String username, String password) {
        Users usuario = new Users();
        usuario.setUsername(username); // Usa o método setUsername()
        usuario.setPassword(password); // Usa o método setPassword()
        usuario.setEnabled(true); // Usa o método setEnabled()
        // Salve o usuário no banco de dados
    }
}