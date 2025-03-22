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

    @Column(name = "nome", nullable = false, unique = true, length = 50)
    private String nome; // Este campo será usado como "username"

    @Column(name = "senha_hash", nullable = false)
    private String senhaHash; // Este campo será usado como "password"

    @Column(name = "habilitado", nullable = false)
    private boolean habilitado = true; // Campo para verificar se o usuário está habilitado

    // Construtor padrão (obrigatório para o JPA)
    public Users() {
    }

    // Construtor com todos os argumentos
    public Users(String nome, String senhaHash, boolean habilitado) {
        this.nome = nome;
        this.senhaHash = senhaHash;
        this.habilitado = habilitado;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    // Métodos da interface UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // Retorna uma lista vazia de autoridades
    }

    @Override
    public String getPassword() {
        return this.senhaHash; // Retorna a senha do usuário
    }

    @Override
    public String getUsername() {
        return this.nome; // Retorna o nome do usuário como "username"
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

    @Override
    public boolean isEnabled() {
        return this.habilitado; // Retorna o status de habilitação do usuário
    }
}