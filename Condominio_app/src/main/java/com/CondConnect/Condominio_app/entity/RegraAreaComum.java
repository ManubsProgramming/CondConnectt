package com.CondConnect.Condominio_app.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "regras_area_comum")
@Data
public class RegraAreaComum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private AreaComum area;

    private String descricao;

    private Integer limitePessoas;
}
