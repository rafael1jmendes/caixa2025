package com.mendes.caixa2025.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mendes.caixa2025.service.ConsultaCaixaService;

@RestController
@RequestMapping("/api/caixa")
public class ConsultaCaixaController {

    @Autowired
    private ConsultaCaixaService caixaService;

    @PostMapping("/abrir")
    public ResponseEntity<String> abrirCaixa(@RequestParam double saldoInicial){
        caixaService.abrirCaixa(saldoInicial);
        return ResponseEntity.ok("Caixa aberto com saldo inicial: " + saldoInicial);
    }
    
    @PostMapping("/entrada")
    public ResponseEntity<String> registrarEntrada(
        @RequestParam double valor,
        @RequestParam String descricao) {
            caixaService.registrarEntrada(valor, descricao);
            return ResponseEntity.ok("Entradas registrada com sucesso");
        }

    @PostMapping("/saida")
    public ResponseEntity<String> registrarSaida (
        @RequestParam double valor,
        @RequestParam String descricao ) {
            caixaService.registrarSaida(valor, descricao);
            return ResponseEntity.ok("Saidas registrada com sucesso");
        }
    
    @GetMapping("/saldo")
    public ResponseEntity<Double> consultarSaldo(){
        return ResponseEntity.ok(caixaService.getSaldoAtual());
    }

    @GetMapping("/historico")
    public ResponseEntity<List<ConsultaCaixaService>> consultarHistorico(
        @RequestParam String inicio,
        @RequestParam String fim) {
            LocalDateTime dataInicio = LocalDateTime.parse (inicio);
            LocalDateTime dataFim = LocalDateTime.parse(fim);
            return ResponseEntity.ok(caixaService.consultaHistorico(dataInicio, dataFim));
        }
    
    @GetMapping("/buscar")
    public ResponseEntity<List<ConsultaCaixaService>> buscarPorDescricao(
        @RequestParam String descricao) {
            return ResponseEntity.ok(caixaService.buscarPorDescricao(descricao));
        }
        

    }
