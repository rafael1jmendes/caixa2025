package com.mendes.caixa2025.repository;

import com.mendes.caixa2025.service.ConsultaCaixaService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mendes.caixa2025.model.ViewFluxoCaixa;

import java.util.List;


@Repository
public interface ViewFluxoCaixaRepository extends JpaRepository<ViewFluxoCaixa, Long>  {
    @Query (value = "SELECT * FROM view_fluxo_caixa "
                        + "v WHERE case when :parametroData is not null then v.data_movimentacao = :parametroData", nativeQuery = true)
    List<ViewFluxoCaixa> dadosCaixa(@Param("parametroData") String parametroData);

    List<ConsultaCaixaService> findByDataBetween(String data);

    List<ConsultaCaixaService> findByDescricaoContainingIgnoreCase(String descricao);
}
