package com.CondConnect.Condominio_app.controller;

import com.CondConnect.Condominio_app.entity.ControleAcesso;
import com.CondConnect.Condominio_app.repository.ControleAcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/controle-acesso")
public class ControleAcessoController {

    @Autowired
    private ControleAcessoRepository controleRepository;

    @GetMapping
    public List<ControleAcesso> listarTodos() {
        return controleRepository.findAll();
    }

    @PostMapping
    public ControleAcesso registrar(@RequestBody ControleAcesso acesso) {
        return controleRepository.save(acesso);
    }
}
