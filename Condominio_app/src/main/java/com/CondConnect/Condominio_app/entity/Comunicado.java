package com.CondConnect.Condominio_app.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "comunicados")
@Data
public class Comunicado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "sindico_id")
    private Usuario sindico;

    private String titulo;

    private String mensagem;

    private LocalDateTime dataEnvio;
}
