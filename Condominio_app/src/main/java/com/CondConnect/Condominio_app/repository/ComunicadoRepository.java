package com.CondConnect.Condominio_app.repository;

import com.CondConnect.Condominio_app.entity.Comunicado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComunicadoRepository extends JpaRepository<Comunicado, Integer> {
    List<Comunicado> findBySindicoId(Integer sindicoId);
}
