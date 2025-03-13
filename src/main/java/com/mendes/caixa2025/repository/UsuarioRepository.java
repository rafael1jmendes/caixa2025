package com.mendes.caixa2025.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mendes.caixa2025.model.Users;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
}