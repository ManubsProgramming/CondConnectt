package com.CondConnect.Condominio_app.controller;

import com.CondConnect.Condominio_app.entity.Encomenda;
import com.CondConnect.Condominio_app.repository.EncomendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/encomendas")
public class EncomendaController {

    @Autowired
    private EncomendaRepository encomendaRepository;

    @GetMapping
    public List<Encomenda> listarTodos() {
        return encomendaRepository.findAll();
    }

    @PostMapping
    public Encomenda criar(@RequestBody Encomenda encomenda) {
        if (encomenda.getDataRecebimento() == null)
            encomenda.setDataRecebimento(LocalDateTime.now());
        if (encomenda.getRetirada() == null)
            encomenda.setRetirada(false);
        return encomendaRepository.save(encomenda);
    }
}
