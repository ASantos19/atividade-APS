package com.example.ativdade_aps.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ativdade_aps.model.Curso;
import com.example.ativdade_aps.repository.CursoRepository;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> listar() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> buscarPorId(Long id) {
        return cursoRepository.findById(id);
    }

    public List<Curso> buscarPorIds(List<Long> ids) {
        return cursoRepository.findAllById(ids);
    }

    public Curso salvar(Curso curso) {
        return cursoRepository.save(curso);
    }

    public void deletar(Long id) {
        cursoRepository.deleteById(id);
    }
}
