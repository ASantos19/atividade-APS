package com.example.ativdade_aps.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ativdade_aps.model.Turma;
import com.example.ativdade_aps.repository.TurmaRepository;

/**
 * Service para operações de negócio da entidade Turma
 */
@Service
@Transactional
public class TurmaService {
    
    @Autowired
    private TurmaRepository turmaRepository;
    
    /**
     * Lista todas as turmas
     * @return lista de todas as turmas
     */
    @Transactional(readOnly = true)
    public List<Turma> listarTodos() {
        return turmaRepository.findAll();
    }
    
    /**
     * Busca uma turma por ID
     * @param id ID da turma
     * @return Optional contendo a turma se encontrada
     */
    @Transactional(readOnly = true)
    public Optional<Turma> buscarPorId(Integer id) {
        return turmaRepository.findById(id);
    }
    
    /**
     * Busca uma turma por código
     * @param codigoTurma código da turma
     * @return Optional contendo a turma se encontrada
     */
    @Transactional(readOnly = true)
    public Optional<Turma> buscarPorCodigo(String codigoTurma) {
        return turmaRepository.findByCodigoTurma(codigoTurma);
    }
    
    /**
     * Cria uma nova turma
     * @param turma turma a ser criada
     * @return turma criada
     * @throws IllegalArgumentException se o código já existe
     */
    public Turma criar(Turma turma) {
        if (turmaRepository.existsByCodigoTurma(turma.getCodigoTurma())) {
            throw new IllegalArgumentException("Já existe uma turma com o código: " + turma.getCodigoTurma());
        }
        return turmaRepository.save(turma);
    }
    
    /**
     * Atualiza uma turma existente
     * @param id ID da turma
     * @param turmaDados dados atualizados da turma
     * @return turma atualizada
     * @throws IllegalArgumentException se a turma não existe ou código já existe
     */
    public Turma atualizar(Integer id, Turma turmaDados) {
        Turma turma = turmaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada com ID: " + id));
        
        // Verifica se o código está sendo alterado e se já existe
        if (!turma.getCodigoTurma().equals(turmaDados.getCodigoTurma()) && 
            turmaRepository.existsByCodigoTurma(turmaDados.getCodigoTurma())) {
            throw new IllegalArgumentException("Já existe uma turma com o código: " + turmaDados.getCodigoTurma());
        }
        
        turma.setCodigoTurma(turmaDados.getCodigoTurma());
        turma.setHorario(turmaDados.getHorario());
        turma.setCurso(turmaDados.getCurso());
        turma.setProfessor(turmaDados.getProfessor());
        
        return turmaRepository.save(turma);
    }
    
    /**
     * Exclui uma turma
     * @param id ID da turma
     * @throws IllegalArgumentException se a turma não existe
     */
    public void excluir(Integer id) {
        if (!turmaRepository.existsById(id)) {
            throw new IllegalArgumentException("Turma não encontrada com ID: " + id);
        }
        turmaRepository.deleteById(id);
    }
    
    /**
     * Busca turmas por curso
     * @param cursoId ID do curso
     * @return lista de turmas do curso
     */
    @Transactional(readOnly = true)
    public List<Turma> buscarPorCurso(Integer cursoId) {
        return turmaRepository.findByCursoId(cursoId);
    }
    
    /**
     * Busca turmas por professor
     * @param professorId ID do professor
     * @return lista de turmas do professor
     */
    @Transactional(readOnly = true)
    public List<Turma> buscarPorProfessor(Integer professorId) {
        return turmaRepository.findByProfessorId(professorId);
    }
    
    /**
     * Busca turmas por horário
     * @param horario horário da turma
     * @return lista de turmas no horário
     */
    @Transactional(readOnly = true)
    public List<Turma> buscarPorHorario(String horario) {
        return turmaRepository.findByHorario(horario);
    }
    
    /**
     * Busca turmas com estudantes matriculados
     * @return lista de turmas com estudantes
     */
    @Transactional(readOnly = true)
    public List<Turma> buscarTurmasComEstudantes() {
        return turmaRepository.findTurmasComEstudantes();
    }
    
    /**
     * Conta o número de estudantes em uma turma
     * @param turmaId ID da turma
     * @return número de estudantes
     */
    @Transactional(readOnly = true)
    public Integer contarEstudantes(Integer turmaId) {
        return turmaRepository.countEstudantesByTurmaId(turmaId);
    }
    
    /**
     * Verifica se uma turma existe
     * @param id ID da turma
     * @return true se existe, false caso contrário
     */
    @Transactional(readOnly = true)
    public boolean existe(Integer id) {
        return turmaRepository.existsById(id);
    }
}
