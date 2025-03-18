package com.mendes.caixa2025.repository;

import com.mendes.caixa2025.model.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username); // Exemplo de método personalizado
}