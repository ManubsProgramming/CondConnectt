package com.CondConnect.Condominio_app.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "moradores")
@Data
public class Morador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "unidade_id")
    private Unidade unidade;

    private String telefone;
}
