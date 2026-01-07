package com.CondConnect.Condominio_app.controller;

import com.CondConnect.Condominio_app.entity.Comunicado;
import com.CondConnect.Condominio_app.entity.Usuario;
import com.CondConnect.Condominio_app.repository.ComunicadoRepository;
import com.CondConnect.Condominio_app.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/comunicados")
public class ComunicadoController {

    @Autowired
    private ComunicadoRepository comunicadoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // ============================
    // LISTAR COMUNICADOS
    // ============================
    @GetMapping
    public List<Comunicado> listar(HttpServletRequest request) {
        String perfil = (String) request.getAttribute("perfil");

        // MORADOR ou SINDICO podem listar
        if ("MORADOR".equals(perfil) || "SINDICO".equals(perfil)) {
            return comunicadoRepository.findAll(); 
        } else {
            throw new RuntimeException("Acesso negado");
        }
    }

    // ============================
    // CRIAR NOVO COMUNICADO
    // ============================
    @PostMapping
    public Comunicado criar(HttpServletRequest request, @RequestBody Comunicado comunicado) {
        String perfil = (String) request.getAttribute("perfil");
        String email = (String) request.getAttribute("email");

        // Apenas SINDICO pode criar comunicados
        if (!"SINDICO".equals(perfil)) {
            throw new RuntimeException("Acesso negado");
        }

        // Define o usuário síndico que está criando
        Usuario sindico = usuarioRepository.findByEmail(email);
        comunicado.setSindico(sindico);

        // Define data de envio atual se não informado
        if (comunicado.getDataEnvio() == null) {
            comunicado.setDataEnvio(LocalDateTime.now());
        }

        return comunicadoRepository.save(comunicado);
    }

    // ============================
    // BUSCAR COMUNICADO POR ID
    // ============================
    @GetMapping("/{id}")
    public Comunicado buscar(@PathVariable Integer id, HttpServletRequest request) {
        String perfil = (String) request.getAttribute("perfil");

        if (!"MORADOR".equals(perfil) && !"SINDICO".equals(perfil)) {
            throw new RuntimeException("Acesso negado");
        }

        return comunicadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comunicado não encontrado"));
    }

    // ============================
    // ATUALIZAR COMUNICADO
    // ============================
    @PutMapping("/{id}")
    public Comunicado atualizar(@PathVariable Integer id, @RequestBody Comunicado comunicadoAtualizado, HttpServletRequest request) {
        String perfil = (String) request.getAttribute("perfil");
        String email = (String) request.getAttribute("email");

        if (!"SINDICO".equals(perfil)) {
            throw new RuntimeException("Acesso negado");
        }

        Comunicado comunicado = comunicadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comunicado não encontrado"));

        // Apenas síndico que criou pode atualizar (opcional)
        if (!comunicado.getSindico().getEmail().equals(email)) {
            throw new RuntimeException("Não autorizado a atualizar este comunicado");
        }

        comunicado.setTitulo(comunicadoAtualizado.getTitulo());
        comunicado.setMensagem(comunicadoAtualizado.getMensagem());
        return comunicadoRepository.save(comunicado);
    }

    // ============================
    // DELETAR COMUNICADO
    // ============================
    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Integer id, HttpServletRequest request) {
        String perfil = (String) request.getAttribute("perfil");
        String email = (String) request.getAttribute("email");

        if (!"SINDICO".equals(perfil)) {
            throw new RuntimeException("Acesso negado");
        }

        Comunicado comunicado = comunicadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comunicado não encontrado"));

        // Apenas síndico que criou pode deletar (opcional)
        if (!comunicado.getSindico().getEmail().equals(email)) {
            throw new RuntimeException("Não autorizado a deletar este comunicado");
        }

        comunicadoRepository.delete(comunicado);
        return "Comunicado deletado com sucesso";
    }
}
