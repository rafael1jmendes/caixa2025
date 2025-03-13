package com.mendes.caixa2025.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.mendes.caixa2025.model.Users;
import com.mendes.caixa2025.repository.UsuarioRepository;
import java.util.Collections;

@Service
@RequiredArgsConstructor // Gera um construtor com todos os campos finais
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users usuario = usuarioRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        return new org.springframework.security.core.userdetails.User(
            usuario.getUsername(), // Usa getUsername()
            usuario.getPassword(), // Usa getPassword()
            usuario.isEnabled(),   // Usa isEnabled()
            true, true, true,      // Account non-expired, credentials non-expired, account non-locked
            Collections.emptyList() // Roles (autoridades)
        );
    }
}