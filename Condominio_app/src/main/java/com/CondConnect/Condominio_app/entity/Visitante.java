package com.CondConnect.Condominio_app.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "visitantes")
@Data
public class Visitante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String documento;

    @ManyToOne
    @JoinColumn(name = "unidade_id")
    private Unidade unidade;
}
