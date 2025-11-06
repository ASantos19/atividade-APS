package com.example.ativdade_aps.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ativdade_aps.model.Estudante;
import com.example.ativdade_aps.repository.EstudanteRepository;

@Service
public class EstudanteService {

    @Autowired
    private EstudanteRepository estudanteRepository;

    public List<Estudante> listar() {
        return estudanteRepository.findAll();
    }

    public Optional<Estudante> buscarPorId(Long id) {
        return estudanteRepository.findById(id);
    }

    public List<Estudante> buscarPorIds(List<Long> ids) {
        return estudanteRepository.findAllById(ids);
    }

    public Estudante salvar(Estudante estudante) {
        return estudanteRepository.save(estudante);
    }

    public void deletar(Long id) {
        estudanteRepository.deleteById(id);
    }
}
