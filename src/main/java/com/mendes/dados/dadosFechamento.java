package com.mendes.dados;

import java.util.HashMap;
import java.util.Map;

public class dadosFechamento {
    private final Map<String, String> campos;

    public dadosFechamento() {
        this.campos = new HashMap<>();
        inicializarCamposFixos();
    }

    private void inicializarCamposFixos() {
        campos.put("Debito Cielo", "");
        campos.put("Credito Cielo", "");
        campos.put("Voucher Cielo", "");
        campos.put("Debito Cielo", "");
        campos.put("Credito Cielo", "");
        campos.put("Voucher Cielo", "");
    }
    
    public void preencherCampo(String nome, String valor) {
        if (campos.containsKey(nome)) {
            campos.put(nome, valor);
        } else {
            System.out.println("Campo não encontrado: "+ nome);
        }

    }

    public String obterValorCampo(String nome) {
        return campos.getOrDefault(nome, "Campo não encontrado");
    }

    public void exibirCampos() {
        for (Map.Entry<String, String> entry : campos.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        dadosFechamento cadastro = new dadosFechamento();
        
        System.out.println("Campos iniciais:");
        cadastro.exibirCampos();
        
        System.out.println("\nPreenchendo valores...");
        cadastro.preencherCampo("Débito Cielo", "100.50");
        cadastro.preencherCampo("Crédito Rede", "250.75");
        
        System.out.println("\nCampos atualizados:");
        cadastro.exibirCampos();
    }
}


 