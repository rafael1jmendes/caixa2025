package com.mendes.caixa2025.repository;

import com.mendes.caixa2025.model.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUsername(String username);

    @Query(value = "SELECT * FROM caixa2025.usuarios u " +
           "WHERE u.username = :username " +
           "AND u.password = pgcrypto.crypt(:password, u.password) " +
           "AND u.enabled = true " +
           "AND u.account_non_expired = true " +
           "AND u.account_non_locked = true " +
           "AND u.credentials_non_expired = true", nativeQuery = true)
    Optional<Users> findByUsernameAndPassword(@Param("username") String username, 
                                           @Param("password") String password);
}