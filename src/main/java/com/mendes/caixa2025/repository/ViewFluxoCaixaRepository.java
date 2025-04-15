package com.mendes.caixa2025.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;
import org.springframework.stereotype.Repository;
import com.mendes.caixa2025.model.ViewFluxoCaixa;


@Repository
public interface ViewFluxoCaixaRepository extends JpaRepositoryExtension<ViewFluxoCaixa, Long>  {

}
