package com.CondConnect.Condominio_app.repository;

import com.CondConnect.Condominio_app.entity.RegraAreaComum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegraAreaComumRepository extends JpaRepository<RegraAreaComum, Integer> {
    List<RegraAreaComum> findByAreaId(Integer areaId);
}
