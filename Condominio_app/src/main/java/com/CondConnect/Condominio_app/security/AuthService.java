package com.CondConnect.Condominio_app.security;

import com.CondConnect.Condominio_app.entity.Usuario;
import com.CondConnect.Condominio_app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public String login(String email, String senha) {
        Usuario usuario = usuarioRepository.findAll()
                .stream()
                .filter(u -> u.getEmail().equals(email) && u.getSenha().equals(senha))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));

        return jwtUtil.gerarToken(usuario.getEmail(), usuario.getPerfil());
    }
}
