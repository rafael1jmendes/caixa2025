package com.mendes.caixa2025.repository;

import com.mendes.caixa2025.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Users, Long> {

    @Query(value = "SELECT * FROM usuarios u " +
                   "WHERE u.username = :username " +
                   "AND u.password = refor.crypt('detrance_' || :password, u.password) " +
                   "AND u.active = true " +
                   "AND u.soft_delete = false", nativeQuery = true)
    Optional<Users> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    Optional<Users> findByUsername(String username); // Busca por username
}