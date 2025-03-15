package com.mendes.caixa2025.service;

import com.mendes.caixa2025.model.Users;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Exemplo: Busque o usuário do banco de dados ou de outra fonte
        Users usuario = buscarUsuarioPorUsername(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return new org.springframework.security.core.userdetails.User(
                usuario.getUsername(), // Usa o método getUsername()
                usuario.getPassword(), // Usa o método getPassword()
                usuario.isEnabled(), // Usa o método isEnabled()
                true, true, true, null);
    }

    private Users buscarUsuarioPorUsername(String username) {
        // Implemente a lógica para buscar o usuário no banco de dados
        // Exemplo fictício:
        Users usuario = new Users();
        usuario.setUsername(username);
        usuario.setPassword("senha123");
        usuario.setEnabled(true);
        return usuario;
    }
}