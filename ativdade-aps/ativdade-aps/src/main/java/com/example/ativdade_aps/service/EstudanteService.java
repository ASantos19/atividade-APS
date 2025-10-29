package com.example.ativdade_aps.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ativdade_aps.model.Estudante;
import com.example.ativdade_aps.repository.EstudanteRepository;

/**
 * Service para operações de negócio da entidade Estudante
 */
@Service
@Transactional
public class EstudanteService {
    
    @Autowired
    private EstudanteRepository estudanteRepository;
    
    /**
     * Lista todos os estudantes
     * @return lista de todos os estudantes
     */
    @Transactional(readOnly = true)
    public List<Estudante> listarTodos() {
        return estudanteRepository.findAll();
    }
    
    /**
     * Busca um estudante por ID
     * @param id ID do estudante
     * @return Optional contendo o estudante se encontrado
     */
    @Transactional(readOnly = true)
    public Optional<Estudante> buscarPorId(Integer id) {
        return estudanteRepository.findById(id);
    }
    
    /**
     * Busca um estudante por matrícula
     * @param matricula matrícula do estudante
     * @return Optional contendo o estudante se encontrado
     */
    @Transactional(readOnly = true)
    public Optional<Estudante> buscarPorMatricula(Integer matricula) {
        return estudanteRepository.findByMatricula(matricula);
    }
    
    /**
     * Cria um novo estudante
     * @param estudante estudante a ser criado
     * @return estudante criado
     * @throws IllegalArgumentException se a matrícula já existe
     */
    public Estudante criar(Estudante estudante) {
        if (estudanteRepository.existsByMatricula(estudante.getMatricula())) {
            throw new IllegalArgumentException("Já existe um estudante com a matrícula: " + estudante.getMatricula());
        }
        return estudanteRepository.save(estudante);
    }
    
    /**
     * Atualiza um estudante existente
     * @param id ID do estudante
     * @param estudanteDados dados atualizados do estudante
     * @return estudante atualizado
     * @throws IllegalArgumentException se o estudante não existe ou matrícula já existe
     */
    public Estudante atualizar(Integer id, Estudante estudanteDados) {
        Estudante estudante = estudanteRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Estudante não encontrado com ID: " + id));
        
        // Verifica se a matrícula está sendo alterada e se já existe
        if (!estudante.getMatricula().equals(estudanteDados.getMatricula()) && 
            estudanteRepository.existsByMatricula(estudanteDados.getMatricula())) {
            throw new IllegalArgumentException("Já existe um estudante com a matrícula: " + estudanteDados.getMatricula());
        }
        
        estudante.setNome(estudanteDados.getNome());
        estudante.setMatricula(estudanteDados.getMatricula());
        
        return estudanteRepository.save(estudante);
    }
    
    /**
     * Exclui um estudante
     * @param id ID do estudante
     * @throws IllegalArgumentException se o estudante não existe
     */
    public void excluir(Integer id) {
        if (!estudanteRepository.existsById(id)) {
            throw new IllegalArgumentException("Estudante não encontrado com ID: " + id);
        }
        estudanteRepository.deleteById(id);
    }
    
    /**
     * Busca estudantes por nome
     * @param nome nome do estudante
     * @return lista de estudantes encontrados
     */
    @Transactional(readOnly = true)
    public List<Estudante> buscarPorNome(String nome) {
        return estudanteRepository.findByNomeContainingIgnoreCase(nome);
    }
    
    /**
     * Busca estudantes matriculados em uma turma específica
     * @param turmaId ID da turma
     * @return lista de estudantes da turma
     */
    @Transactional(readOnly = true)
    public List<Estudante> buscarPorTurma(Integer turmaId) {
        return estudanteRepository.findByTurmaId(turmaId);
    }
    
    /**
     * Busca estudantes que não estão matriculados em nenhuma turma
     * @return lista de estudantes sem turma
     */
    @Transactional(readOnly = true)
    public List<Estudante> buscarEstudantesSemTurma() {
        return estudanteRepository.findEstudantesSemTurma();
    }
    
    /**
     * Conta o número de turmas de um estudante
     * @param estudanteId ID do estudante
     * @return número de turmas
     */
    @Transactional(readOnly = true)
    public Integer contarTurmas(Integer estudanteId) {
        return estudanteRepository.countTurmasByEstudanteId(estudanteId);
    }
    
    /**
     * Verifica se um estudante existe
     * @param id ID do estudante
     * @return true se existe, false caso contrário
     */
    @Transactional(readOnly = true)
    public boolean existe(Integer id) {
        return estudanteRepository.existsById(id);
    }
}
