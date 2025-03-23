package com.mendes.caixa2025.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "usuarios", schema = "caixa2025")
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username; // Campo para o username

    @Column(name = "password", nullable = false)
    private String password; // Campo para a senha

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true; // Campo para verificar se o usuário está ativo

    // Construtor padrão (obrigatório para o JPA)
    public Users() {
    }

    // Construtor com todos os argumentos
    public Users(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    // Métodos da interface UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // Retorna uma lista vazia de autoridades
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Define se a conta não expirou
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Define se a conta não está bloqueada
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Define se as credenciais não expiraram
    }
}