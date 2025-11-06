package com.example.ativdade_aps.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ativdade_aps.model.Turma;
import com.example.ativdade_aps.repository.TurmaRepository;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    public List<Turma> listar() {
        return turmaRepository.findAll();
    }

    public Optional<Turma> buscarPorId(Long id) {
        return turmaRepository.findById(id);
    }

    public List<Turma> buscarPorIds(List<Long> ids) {
        return turmaRepository.findAllById(ids);
    }

    public Turma salvar(Turma turma) {
        return turmaRepository.save(turma);
    }

    public void deletar(Long id) {
        turmaRepository.deleteById(id);
    }

    public Object toDTO(Turma turma) {
        throw new UnsupportedOperationException("Unimplemented method 'toDTO'");
    }
}