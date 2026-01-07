package com.CondConnect.Condominio_app.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "areas_comuns")
@Data
public class AreaComum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String descricao;
}
