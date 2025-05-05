package com.mendes.caixa2025.model;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

import com.mendes.caixa2025.helper.TipoMovimento;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "caixa2025", name = "fluxo_caixa")
@Getter
@Setter
public class ConsultaCaixa { 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime data;
    private String descricao;
    private double valor;
    private double SaldoAtual;

    @Enumerated(EnumType.STRING)
    private TipoMovimento tipo;

//    public Long getId() {
//        return id;
//    }
//    public void setId(Long id) {
//        this.id = id;
//    }
//    public LocalDateTime getData() {
//        return data;
//    }
//    public void setData(Date data) {
//        this.data = data;
//    }
//    public String getDescricao() {
//        return descricao;
//    }
//    public void setDescricao(String descricao) {
//        this.descricao = descricao;
//    }
//    public BigDecimal getValor() {
//        return valor;
//    }
//    public void setValor(BigDecimal valor) {
//        this.valor = valor;
//    }
//    public TipoMovimento getTipoMovimento() {
//        return tipoMovimento;
//    }
//    public void setTipoMovimento(TipoMovimento tipoMovimento) {
//        this.tipoMovimento = tipoMovimento;
//    }

}
