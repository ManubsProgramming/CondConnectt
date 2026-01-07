package com.CondConnect.Condominio_app.controller;

import com.CondConnect.Condominio_app.entity.Morador;
import com.CondConnect.Condominio_app.entity.Usuario;
import com.CondConnect.Condominio_app.repository.MoradorRepository;
import com.CondConnect.Condominio_app.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moradores")
public class MoradorController {

    @Autowired
    private MoradorRepository moradorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/meus-dados")
    public Morador meusDados(HttpServletRequest request) {
        String email = (String) request.getAttribute("email");
        Usuario usuario = usuarioRepository.findByEmail(email);
        return moradorRepository.findById(usuario.getId()).orElse(null);
    }

    @GetMapping
    public List<Morador> listarTodos(HttpServletRequest request) {
        String perfil = (String) request.getAttribute("perfil");
        if ("SINDICO".equals(perfil)) {
            return moradorRepository.findAll();
        } else {
            throw new RuntimeException("Acesso negado");
        }
    }
}
