package com.CondConnect.Condominio_app.repository;

import com.CondConnect.Condominio_app.entity.AreaComum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaComumRepository extends JpaRepository<AreaComum, Integer> {
}
