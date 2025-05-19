package com.mendes.caixa2025.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "rules", schema = "caixa2025")
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ruleName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String entity;  // Ex: "Caixa", "Users"

    @Column(nullable = false)
    private String operation; // Ex: "CREATE", "READ", "UPDATE", "DELETE"

    @Column(nullable = false)
    private String condition; // Ex: "valor > 1000", "tipoMovimento = 'ENTRADA'"

    @ManyToMany(mappedBy = "rules")
    private Set<Users> users;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;

}