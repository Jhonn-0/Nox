package com.Nox.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "consultas")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora;

    @ManyToOne
    private Paciente paciente;

    @ManyToOne
    private Medico medico;

    private String status;

    public Consulta() {}

    public Consulta(LocalDateTime dataHora, Paciente paciente, Medico medico, String status) {
        this.dataHora = dataHora;
        this.paciente = paciente;
        this.medico = medico;
        this.status = status;
    }

    // getters e setters (depois você pode gerar automático)
}
