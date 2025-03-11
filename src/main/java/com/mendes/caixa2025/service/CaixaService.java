package com.mendes.caixa2025.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mendes.caixa2025.model.Caixa;
import com.mendes.caixa2025.repository.CaixaRepository;

@Service
public class CaixaService { 

    @Autowired
    private CaixaRepository caixaRepository;

    public List<Caixa> listarTodos (){
        return caixaRepository.findAll();
    }
    public Caixa salvar (Caixa caixa){
        return caixaRepository.save(caixa);
    }
    public Caixa atualizar(Caixa caixa) {
        return caixaRepository.save(caixa);
    }
    public void excluir(Long id) {
        caixaRepository.deleteById(id);
    }
}
