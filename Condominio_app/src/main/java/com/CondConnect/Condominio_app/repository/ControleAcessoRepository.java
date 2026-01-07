package com.CondConnect.Condominio_app.repository;

import com.CondConnect.Condominio_app.entity.ControleAcesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ControleAcessoRepository extends JpaRepository<ControleAcesso, Integer> {
    List<ControleAcesso> findByVisitanteId(Integer visitanteId);
    List<ControleAcesso> findByPorteiroId(Integer porteiroId);
}
