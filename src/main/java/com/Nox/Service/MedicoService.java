package com.Nox.Service;

import com.Nox.Model.Medico;
import com.Nox.Repository.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {
    private  final MedicoRepository repository;

    public MedicoService(MedicoRepository repository){
        this.repository = repository;
    }
    public Medico criar(Medico medico){
        return repository.save(medico);
    }
    public List<Medico>listar(){
        return repository.findAll();
    }

}
