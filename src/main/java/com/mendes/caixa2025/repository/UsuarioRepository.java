package com.mendes.caixa2025.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mendes.caixa2025.model.Users; // Importação correta

public interface UsuarioRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
}