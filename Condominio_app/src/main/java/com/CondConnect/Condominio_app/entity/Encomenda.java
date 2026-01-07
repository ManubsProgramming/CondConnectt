package com.CondConnect.Condominio_app.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "encomendas")
@Data
public class Encomenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "morador_id")
    private Morador morador;

    @ManyToOne
    @JoinColumn(name = "porteiro_id")
    private Usuario porteiro;

    private String descricao;

    private LocalDateTime dataRecebimento;

    private Boolean retirada;
}
