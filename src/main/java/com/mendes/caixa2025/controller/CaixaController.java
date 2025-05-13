package com.mendes.caixa2025.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mendes.caixa2025.service.ConsultaCaixaService;

@RestController
@RequestMapping("/api/caixa")
public class CaixaController {

    @Autowired
    private ConsultaCaixaService caixaService;

//    @PostMapping("/abrir")
//    public ResponseEntity<String> abrirCaixa(@RequestParam double saldoInicial){
//        caixaService.abrirCaixa(saldoInicial);
//        return ResponseEntity.ok("Caixa aberto com saldo inicial: " + saldoInicial);
//    }
    
//    @PostMapping("/entrada")
//    public ResponseEntity<String> registrarEntrada(
//        @RequestParam double valor,
//        @RequestParam String descricao) {
//            caixaService.registrarEntrada(valor, descricao);
//            return ResponseEntity.ok("Entradas registrada com sucesso");
//        }

//    @PostMapping("/saida")
//    public ResponseEntity<String> registrarSaida (
//        @RequestParam double valor,
//        @RequestParam String descricao ) {
//            caixaService.registrarSaida(valor, descricao);
//            return ResponseEntity.ok("Saidas registrada com sucesso");
//        }
    
//    @GetMapping("/saldo")
//    public ResponseEntity<Double> consultarSaldo(){
//        return ResponseEntity.ok(caixaService.getSaldoAtual());
//    }

    @GetMapping("/historico")
    public ResponseEntity<List<ConsultaCaixaService>> consultarHistorico() {
            return ResponseEntity.ok(caixaService.consultaHistorico("2025-05-01"));
        }
    
//    @GetMapping("/buscar")
//    public ResponseEntity<List<ConsultaCaixaService>> buscarPorDescricao(
//        @RequestParam String descricao) {
//            return ResponseEntity.ok(caixaService.buscarPorDescricao(descricao));
//        }
        

    }
