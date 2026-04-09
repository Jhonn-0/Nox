package com.Nox.Controller;

import com.Nox.Model.Consulta;
import com.Nox.Service.ConsultaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    private final ConsultaService service;

    public ConsultaController(ConsultaService service) {
        this.service = service;
    }

    @PostMapping
    public Consulta agendar(
            @RequestParam Long pacienteId,
            @RequestParam Long medicoId,
            @RequestParam String dataHora) {

        return service.agendar(
                pacienteId,
                medicoId,
                LocalDateTime.parse(dataHora)
        );
    }
}
