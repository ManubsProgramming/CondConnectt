
package com.CondConnect.Condominio_app.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "unidades")
@Data
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String bloco;

    private String numero;
}
