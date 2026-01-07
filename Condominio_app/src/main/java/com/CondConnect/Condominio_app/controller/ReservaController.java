package com.CondConnect.Condominio_app.controller;

import com.CondConnect.Condominio_app.entity.ReservaAreaComum;
import com.CondConnect.Condominio_app.repository.ReservaAreaComumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaAreaComumRepository reservaRepository;

    @GetMapping
    public List<ReservaAreaComum> listarTodas() {
        return reservaRepository.findAll();
    }

    @PostMapping
    public ReservaAreaComum criar(@RequestBody ReservaAreaComum reserva) {
        if (reserva.getStatus() == null) reserva.setStatus("PENDENTE");
        return reservaRepository.save(reserva);
    }

    @PutMapping("/{id}")
    public ReservaAreaComum atualizar(@PathVariable Integer id, @RequestBody ReservaAreaComum reserva) {
        reserva.setId(id);
        return reservaRepository.save(reserva);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        reservaRepository.deleteById(id);
    }
}
