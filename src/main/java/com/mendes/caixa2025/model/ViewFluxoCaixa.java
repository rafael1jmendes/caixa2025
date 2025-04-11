package com.mendes.caixa2025.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema = "caixa2025" , name = "view_fluxo_caixa")
public class ViewFluxoCaixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "descricao", nullable = false, unique = true, length = 50)
    private String descricao; // Campo para o descricao

    @Column(name = "tipo", nullable = false)
    private String tipo; // Campo para a tipo

    @Column(name = "valor", nullable = false)
    private boolean valor = true; 

    @Column(name = "data_movimentacao", nullable = false)
    private  boolean dataMovimentacao = true; 

    @Column(name = "categoria", nullable = false)
    private Integer  categoria; 

    @Column(name = "criado_em", nullable = false)
    private boolean criadoEm = true;

    public ViewFluxoCaixa(String descricao, String tipo, boolean valor, boolean dataMovimentacao, Integer categoria,
            boolean criadoEm) {
        this.descricao = descricao;
        this.tipo = tipo;
        this.valor = valor;
        this.dataMovimentacao = dataMovimentacao;
        this.categoria = categoria;
        this.criadoEm = criadoEm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isValor() {
        return valor;
    }

    public void setValor(boolean valor) {
        this.valor = valor;
    }

    public boolean isDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(boolean dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }


    public boolean isCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(boolean criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }



    
    
}