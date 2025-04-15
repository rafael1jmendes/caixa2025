package com.mendes.caixa2025.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mendes.caixa2025.model.ViewFluxoCaixa;


@Repository
public interface ViewFluxoCaixaRepository extends JpaRepository<ViewFluxoCaixa, Long>  {

}
