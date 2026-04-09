package com.Nox.Service;

import com.Nox.Model.Paciente;
import com.Nox.Repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    public Paciente criar(Paciente paciente) {

        // 🔥 Regra de negócio: não permitir email duplicado
        repository.findByEmail(paciente.getEmail())
                .ifPresent(p -> {
                    throw new RuntimeException("Email já cadastrado");
                });

        return repository.save(paciente);
    }

    public List<Paciente> listar() {
        return repository.findAll();
    }

    public Paciente buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}