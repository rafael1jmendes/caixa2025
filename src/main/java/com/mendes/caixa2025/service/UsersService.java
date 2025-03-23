package com.mendes.caixa2025.service;

import com.mendes.caixa2025.model.Users;
import com.mendes.caixa2025.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UsuarioRepository usuarioRepository;

    public UsersService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void enableUser(Long userId) {
        Users user = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        user.setEnabled(true); // Habilita o usuário
        usuarioRepository.save(user);
    }

    public void disableUser(Long userId) {
        Users user = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        user.setEnabled(false); // Desabilita o usuário
        usuarioRepository.save(user);
    }
}