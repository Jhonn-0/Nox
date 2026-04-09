package com.Nox.Controller;

import com.Nox.Dto.CadastroDTO;
import com.Nox.Dto.LoginDTO;
import com.Nox.Model.Paciente;
import com.Nox.Service.PacienteService;
import com.Nox.Repository.PacienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService service;
    private final PacienteRepository pacienteRepository;

    public PacienteController(PacienteService servicePaciente, PacienteRepository pacienteRepository) {
        this.service = servicePaciente;
        this.pacienteRepository = pacienteRepository;
    }

    @PostMapping
    public Paciente criar(@RequestBody Paciente paciente) {
        return service.criar(paciente);
    }

    @GetMapping
    public List<Paciente> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Paciente buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {

         Optional<Paciente> pacienteOptional = pacienteRepository.findByEmail(dto.email);

        if (pacienteOptional.isEmpty()) {
            return ResponseEntity.status(404).body("Usuário não encontrado");
        }

        Paciente paciente = pacienteOptional.get();

        if (!paciente.getSenha().equals(dto.senha)) {
            return ResponseEntity.status(401).body("Senha inválida");
        }

        return ResponseEntity.ok(paciente);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastro(@RequestBody CadastroDTO dto) {
        if (pacienteRepository.findByEmail(dto.email()).isPresent()) {
            return ResponseEntity.status(409).body("Usuário já existe");
        }

        Paciente paciente = new Paciente(
                dto.nome(),
                dto.email(),
                dto.senha(),
                dto.telefone()
        );
        pacienteRepository.save(paciente);

        return ResponseEntity.ok(paciente);
    }


}