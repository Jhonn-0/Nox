package com.Nox.Service;


import com.Nox.Model.Consulta;
import com.Nox.Model.Medico;
import com.Nox.Model.Paciente;
import com.Nox.Repository.ConsultaRepository;
import com.Nox.Repository.MedicoRepository;
import com.Nox.Repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConsultaService {
    private final ConsultaRepository repository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    public ConsultaService(
            ConsultaRepository repository,
            PacienteRepository pacienteRepository,
            MedicoRepository medicoRepository) {

        this.repository = repository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }

    public Consulta agendar(Long pacienteId, Long medicoId, LocalDateTime dataHora) {

        // 🔥 REGRA: evitar conflito
        boolean existe = repository.existsByMedicoIdAndDataHora(medicoId, dataHora);

        if (existe) {
            throw new RuntimeException("Horário já ocupado para este médico");
        }

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        Consulta consulta = new Consulta(
                dataHora,
                paciente,
                medico,
                "AGENDADA"
        );

        return repository.save(consulta);
    }
}
