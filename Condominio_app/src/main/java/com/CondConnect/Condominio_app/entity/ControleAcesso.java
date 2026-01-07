package com.CondConnect.Condominio_app.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "controle_acesso")
@Data
public class ControleAcesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "visitante_id")
    private Visitante visitante;

    @ManyToOne
    @JoinColumn(name = "porteiro_id")
    private Usuario porteiro;

    private LocalDateTime entrada;

    private LocalDateTime saida;
}
