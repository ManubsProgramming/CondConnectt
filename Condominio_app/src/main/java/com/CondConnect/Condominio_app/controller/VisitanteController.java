package com.CondConnect.Condominio_app.controller;

import com.CondConnect.Condominio_app.entity.Morador;
import com.CondConnect.Condominio_app.entity.Unidade;
import com.CondConnect.Condominio_app.entity.Usuario;
import com.CondConnect.Condominio_app.entity.Visitante;
import com.CondConnect.Condominio_app.repository.MoradorRepository;
import com.CondConnect.Condominio_app.repository.UnidadeRepository;
import com.CondConnect.Condominio_app.repository.UsuarioRepository;
import com.CondConnect.Condominio_app.repository.VisitanteRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visitantes")
public class VisitanteController {

    @Autowired
    private VisitanteRepository visitanteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MoradorRepository moradorRepository;

    @Autowired
    private UnidadeRepository unidadeRepository;

    // ============================
    // LISTAR VISITANTES
    // ============================
    @GetMapping
    public List<Visitante> listar(HttpServletRequest request) {
        String perfil = (String) request.getAttribute("perfil");
        String email = (String) request.getAttribute("email");

        if ("MORADOR".equals(perfil)) {
            // encontra o morador logado
            Usuario usuario = usuarioRepository.findByEmail(email);
            Morador morador = moradorRepository.findByUsuario(usuario);
            return visitanteRepository.findByUnidade(morador.getUnidade());
        } else if ("PORTEIRO".equals(perfil) || "SINDICO".equals(perfil)) {
            return visitanteRepository.findAll();
        } else {
            throw new RuntimeException("Acesso negado");
        }
    }


    @PreAuthorize("hasAuthority('MORADOR')")
    @PostMapping("/{id}/autorizar")
    public ResponseEntity<String> autorizarVisitante(@PathVariable Integer id) {
        // lógica para autorizar visitante
        return ResponseEntity.ok("Visitante autorizado");
    }

    // ============================
    // CRIAR VISITANTE
    // ============================
    @PostMapping
    public Visitante criar(HttpServletRequest request, @RequestBody Visitante visitante) {
        String perfil = (String) request.getAttribute("perfil");
        String email = (String) request.getAttribute("email");

        if (!"MORADOR".equals(perfil) && !"SINDICO".equals(perfil)) {
            throw new RuntimeException("Acesso negado");
        }

        // Se for MORADOR, define a unidade automaticamente
        if ("MORADOR".equals(perfil)) {
            Usuario usuario = usuarioRepository.findByEmail(email);
            Morador morador = moradorRepository.findByUsuario(usuario);
            visitante.setUnidade(morador.getUnidade());
        } else if ("SINDICO".equals(perfil)) {
            // SÍNDICO deve informar a unidade manualmente no JSON
            if (visitante.getUnidade() == null) {
                throw new RuntimeException("Informe a unidade do visitante");
            } else {
                // valida se a unidade existe
                Unidade unidade = unidadeRepository.findById(visitante.getUnidade().getId())
                        .orElseThrow(() -> new RuntimeException("Unidade não encontrada"));
                visitante.setUnidade(unidade);
            }
        }

        return visitanteRepository.save(visitante);
    }

    // ============================
    // BUSCAR VISITANTE POR ID
    // ============================
    @GetMapping("/{id}")
    public Visitante buscar(@PathVariable Integer id, HttpServletRequest request) {
        String perfil = (String) request.getAttribute("perfil");
        String email = (String) request.getAttribute("email");

        Visitante visitante = visitanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visitante não encontrado"));

        if ("MORADOR".equals(perfil)) {
            Usuario usuario = usuarioRepository.findByEmail(email);
            Morador morador = moradorRepository.findByUsuario(usuario);
            if (!visitante.getUnidade().equals(morador.getUnidade())) {
                throw new RuntimeException("Acesso negado");
            }
        } else if (!"PORTEIRO".equals(perfil) && !"SINDICO".equals(perfil)) {
            throw new RuntimeException("Acesso negado");
        }

        return visitante;
    }

    // ============================
    // ATUALIZAR VISITANTE
    // ============================
    @PutMapping("/{id}")
    public Visitante atualizar(@PathVariable Integer id, @RequestBody Visitante visitanteAtualizado, HttpServletRequest request) {
        String perfil = (String) request.getAttribute("perfil");
        String email = (String) request.getAttribute("email");

        Visitante visitante = visitanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visitante não encontrado"));

        if ("MORADOR".equals(perfil)) {
            Usuario usuario = usuarioRepository.findByEmail(email);
            Morador morador = moradorRepository.findByUsuario(usuario);
            if (!visitante.getUnidade().equals(morador.getUnidade())) {
                throw new RuntimeException("Acesso negado");
            }
        } else if (!"SINDICO".equals(perfil)) {
            throw new RuntimeException("Acesso negado");
        }

        visitante.setNome(visitanteAtualizado.getNome());
        visitante.setDocumento(visitanteAtualizado.getDocumento());
        // para SÍNDICO, permite atualizar a unidade
        if ("SINDICO".equals(perfil) && visitanteAtualizado.getUnidade() != null) {
            visitante.setUnidade(visitanteAtualizado.getUnidade());
        }

        return visitanteRepository.save(visitante);
    }

    // ============================
    // DELETAR VISITANTE
    // ============================
    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Integer id, HttpServletRequest request) {
        String perfil = (String) request.getAttribute("perfil");
        String email = (String) request.getAttribute("email");

        Visitante visitante = visitanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visitante não encontrado"));

        if ("MORADOR".equals(perfil)) {
            Usuario usuario = usuarioRepository.findByEmail(email);
            Morador morador = moradorRepository.findByUsuario(usuario);
            if (!visitante.getUnidade().equals(morador.getUnidade())) {
                throw new RuntimeException("Acesso negado");
            }
        } else if (!"SINDICO".equals(perfil) && !"PORTEIRO".equals(perfil)) {
            throw new RuntimeException("Acesso negado");
        }

        visitanteRepository.delete(visitante);
        return "Visitante deletado com sucesso";
    }
}
