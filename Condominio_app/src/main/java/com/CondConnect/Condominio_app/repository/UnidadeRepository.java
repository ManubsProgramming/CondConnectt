package com.CondConnect.Condominio_app.repository;

import com.CondConnect.Condominio_app.entity.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Integer> {
}
