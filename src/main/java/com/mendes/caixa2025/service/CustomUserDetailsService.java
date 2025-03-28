package com.mendes.caixa2025.service;

import com.mendes.caixa2025.model.Users;
import com.mendes.caixa2025.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca o usuário pelo username
        Users usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        return usuario; // Retorna o usuário, que já implementa UserDetails
    }

    public UserDetails loadUserByUsernameAndPassword(String username, String password) throws UsernameNotFoundException {
        // Busca o usuário pelo username
        Users usuario = usuarioRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        return usuario; // Retorna o usuário, que já implementa UserDetails
    }
}