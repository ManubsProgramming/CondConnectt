package com.CondConnect.Condominio_app.repository;

import com.CondConnect.Condominio_app.entity.ReservaAreaComum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaAreaComumRepository extends JpaRepository<ReservaAreaComum, Integer> {
    List<ReservaAreaComum> findByMoradorId(Integer moradorId);
    List<ReservaAreaComum> findByAreaIdAndDataReserva(Integer areaId, LocalDate dataReserva);
}
