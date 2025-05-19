package com.mendes.caixa2025.service;

import java.security.PublicKey;
import java.time.LocalDateTime;
import java.util.List;

import com.mendes.caixa2025.model.ViewFluxoCaixa;
import com.mendes.caixa2025.repository.CaixaRepository;
import com.mendes.caixa2025.repository.ViewFluxoCaixaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mendes.caixa2025.helper.TipoMovimento;
import com.mendes.caixa2025.model.ConsultaCaixa;

import jakarta.transaction.Transactional;

@Service

public class ConsultaCaixaService {

    @Autowired
    private ViewFluxoCaixaRepository viewFluxoCaixaRepository;

    @Autowired
    private CaixaRepository caixaRepository;

    private double saldoAtual;

    @Transactional
    public void abrirCaixa(double saldoInicial){
        this.saldoAtual = saldoInicial;
        registrarOperacao("Abertura de caixa", saldoInicial, TipoMovimento.ABERTURA);
    }

    @Transactional
    public void registrarEntrada(double valor, String descricao){
        if (valor <= 0) {
            throw new IllegalArgumentException("VALOR DE ENTRADA DEVE SER POSITIVO");
        }
        this.saldoAtual += valor;
        registrarOperacao(descricao, valor, TipoMovimento.ENTRADA);
    }

    @Transactional
    public void registrarSaida(double valor, String descricao){
        if(valor <= 0) {
            throw new IllegalArgumentException("VALOR DE SAIDA DEVE SER POSITIVO");
        }
        if (valor <=0) {
            throw new IllegalStateException("SALDO INSUFICIENTE");
        }
        this.saldoAtual -= valor;
        registrarOperacao(descricao, -valor, TipoMovimento.SAIDA);
    }

    @Transactional
    public void fecharCaixa(){
        registrarOperacao("Fechamento de Caixa ", 0, TipoMovimento.FECHAMENTO);
    }

    private void registrarOperacao (String descricao, double valor, TipoMovimento tipo) {
        ConsultaCaixa operacao = new ConsultaCaixa();
       caixaRepository.save(operacao);
    }
    public double getSaldoAtual() {
        return saldoAtual;
    }
    public List<ConsultaCaixaService> consultaHistorico(String data) {
        List<ViewFluxoCaixa> movimentoCaixa = viewFluxoCaixaRepository.dadosCaixa(data);
       System.out.println(movimentoCaixa.get(0).isValor());
        return viewFluxoCaixaRepository.findByDataBetween(data);
    }
    public List<ConsultaCaixaService> buscarPorDescricao(String descricao){
        return viewFluxoCaixaRepository.findByDescricaoContainingIgnoreCase(descricao);
    }

}
   