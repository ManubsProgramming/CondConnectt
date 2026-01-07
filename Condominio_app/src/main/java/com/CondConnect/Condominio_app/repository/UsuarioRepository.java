package com.CondConnect.Condominio_app.repository;

import com.CondConnect.Condominio_app.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Busca usuário pelo email (para login e JWT)
    Usuario findByEmail(String email);
}
