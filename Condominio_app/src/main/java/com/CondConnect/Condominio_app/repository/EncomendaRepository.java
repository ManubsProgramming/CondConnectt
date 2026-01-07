package com.CondConnect.Condominio_app.repository;

import com.CondConnect.Condominio_app.entity.Encomenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EncomendaRepository extends JpaRepository<Encomenda, Integer> {
    List<Encomenda> findByMoradorId(Integer moradorId);
    List<Encomenda> findByRetiradaFalse();
}
