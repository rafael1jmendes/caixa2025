package com.mendes.caixa2025.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mendes.caixa2025.model.Rule;
import com.mendes.caixa2025.repository.RuleRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class RuleService {
    
    private final RuleRepository rulesRepository;
    
    @PersistenceContext
    private EntityManager entityManager;

    public RuleService(RuleRepository rulesRepository) {
        this.rulesRepository = rulesRepository;
    }

    public boolean checkRules(String entity, String operation, Object entityInstance) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Rule> rules = rulesRepository.findByUsername(username);
        
        return rules.stream()
            .filter(r -> r.getEntity().equalsIgnoreCase(entity))
            .filter(r -> r.getOperation().equalsIgnoreCase(operation))
            .allMatch(r -> evaluateCondition(r.getCondition(), entityInstance));
    }

    private boolean evaluateCondition(String condition, Object entityInstance) {
        if (condition == null || condition.isEmpty()) {
            return true;
        }

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        var query = cb.createQuery(Boolean.class);
        Root<?> root = query.from(entityInstance.getClass());
        
        try {
            Predicate predicate = cb.isTrue(cb.literal(true));
            // Implementação simplificada - considere usar uma biblioteca como Spring EL
            // para avaliação segura de expressões
            if (condition.contains("valor >")) {
                String value = condition.split(">")[1].trim();
                predicate = cb.greaterThan(root.get("valor"), new BigDecimal(value));
            }
            // Adicione mais condições conforme necessário
            
            return entityManager.createQuery(query.select(predicate)).getSingleResult();
        } catch (Exception e) {
            return false;
        }
    }
}