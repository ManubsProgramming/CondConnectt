package com.CondConnect.Condominio_app.repository;

import com.CondConnect.Condominio_app.entity.Morador;
import com.CondConnect.Condominio_app.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoradorRepository extends JpaRepository<Morador, Integer> {

    // Busca morador pelo objeto Usuario
    Morador findByUsuario(Usuario usuario);
}

