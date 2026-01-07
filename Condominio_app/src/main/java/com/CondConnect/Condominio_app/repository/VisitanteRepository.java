package com.CondConnect.Condominio_app.repository;

import com.CondConnect.Condominio_app.entity.Unidade;
import com.CondConnect.Condominio_app.entity.Visitante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitanteRepository extends JpaRepository<Visitante, Integer> {
    // Busca todos os visitantes de uma unidade
    List<Visitante> findByUnidade(Unidade unidade);
}
