package com.mendes.caixa2025.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mendes.caixa2025.model.ConsultaCaixa;

public interface CaixaRepository extends JpaRepository<ConsultaCaixa, Long> {

    
}