package com.CondConnect.Condominio_app.controller;

import com.CondConnect.Condominio_app.entity.Usuario;
import com.CondConnect.Condominio_app.repository.UsuarioRepository;
import com.CondConnect.Condominio_app.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // injetando o encoder

    @PostMapping("/login")
public Map<String,String> login(@RequestBody Map<String,String> dados) {
    String email = dados.get("email");
    String senha = dados.get("senha");

    Usuario usuario = usuarioRepository.findByEmail(email);
    if(usuario==null || !passwordEncoder.matches(senha, usuario.getSenha())) {
        throw new RuntimeException("Credenciais inválidas");
    }

    String token = jwtUtil.gerarToken(usuario.getEmail(), usuario.getPerfil());

    return Map.of("token", token, "nome", usuario.getNome(), "perfil", usuario.getPerfil());
}



    @PostMapping("/register")
   public Map<String,String> register(@RequestBody Map<String,String> dados) {
    String email = dados.get("email");
    String senha = dados.get("senha");
    String nome = dados.get("nome");
    String perfil = dados.get("perfil"); // MORADOR, SINDICO, PORTEIRO

    if (usuarioRepository.findByEmail(email) != null) {
        throw new RuntimeException("Usuário já existe");
    }

    Usuario u = new Usuario();
    u.setEmail(email);
    u.setNome(nome);
    u.setPerfil(perfil);
    u.setSenha(passwordEncoder.encode(senha)); // senha criptografada

    usuarioRepository.save(u);

    return Map.of("mensagem","Usuário criado", "email", email, "perfil", perfil);
}
} 
