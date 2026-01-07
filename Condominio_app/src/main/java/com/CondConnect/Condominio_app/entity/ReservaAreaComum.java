package com.CondConnect.Condominio_app.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "reservas_area_comum")
@Data
public class ReservaAreaComum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "morador_id")
    private Morador morador;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private AreaComum area;

    private LocalDate dataReserva;

    private String horario;

    private String status; // PENDENTE, APROVADA, CANCELADA
}
