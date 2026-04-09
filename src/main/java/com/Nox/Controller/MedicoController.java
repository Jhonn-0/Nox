package com.Nox.Controller;

import com.Nox.Model.Medico;
import com.Nox.Service.MedicoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoService service;

    public MedicoController(MedicoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Medico> listar() {
        return service.listar();
    }
    @PostMapping
    public Medico criar(@RequestBody Medico medico) {
        return service.criar(medico);
    }
}
