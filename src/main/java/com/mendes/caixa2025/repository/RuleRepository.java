package com.mendes.caixa2025.repository;

import com.mendes.caixa2025.model.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Long> {
    
    @Query("SELECT r FROM Rules r WHERE r.entity = :entity AND r.operation = :operation")
    List<Rule> findByEntityAndOperation(@Param("entity") String entity, 
                                        @Param("operation") String operation);
    
    @Query("SELECT r FROM Rules r JOIN r.users u WHERE u.username = :username")
    List<Rule> findByUsername(@Param("username") String username);
}