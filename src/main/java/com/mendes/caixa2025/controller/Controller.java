//package com.mendes.caixa2025.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.mendes.caixa2025.model.ConsultaCaixa;
//import com.mendes.caixa2025.service.CaixaService;
//
//@RestController
//@RequestMapping("/caixa")
//public class Controller {
//
//    @Autowired
//    private CaixaService caixaService;
//
//    @GetMapping
//    public List<ConsultaCaixa> listarTodos() {
//        return caixaService.listarTodos();
//    }
//
//    @PostMapping
//    public ConsultaCaixa salvar(@RequestBody ConsultaCaixa caixa){
//        return caixaService.salvar(caixa);
//    }
//
//    @PutMapping("/{id}")
//    public ConsultaCaixa atualizar(@PathVariable Long id, @RequestBody ConsultaCaixa caixa) {
//        ConsultaCaixa caixaExistente = caixaService.listarTodos().stream().filter
//                    (c-> c.getId().equals(id)).findFirst().orElseThrow();
//        caixa.setId(caixaExistente.getId());
//                return caixaService.atualizar(caixa);
//    }
//
//    @DeleteMapping("/{id}")
//    public void excluir (@PathVariable Long id) {
//            caixaService.excluir(id);
//    }
//}
//
