package com.example.ativdade_aps.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ativdade_aps.model.Professor;
import com.example.ativdade_aps.repository.ProfessorRepository;

/**
 * Service para operações de negócio da entidade Professor
 */
@Service
@Transactional
public class ProfessorService {
    
    @Autowired
    private ProfessorRepository professorRepository;
    
    /**
     * Lista todos os professores
     * @return lista de todos os professores
     */
    @Transactional(readOnly = true)
    public List<Professor> listarTodos() {
        return professorRepository.findAll();
    }
    
    /**
     * Busca um professor por ID
     * @param id ID do professor
     * @return Optional contendo o professor se encontrado
     */
    @Transactional(readOnly = true)
    public Optional<Professor> buscarPorId(Integer id) {
        return professorRepository.findById(id);
    }
    
    /**
     * Cria um novo professor
     * @param professor professor a ser criado
     * @return professor criado
     */
    public Professor criar(Professor professor) {
        return professorRepository.save(professor);
    }
    
    /**
     * Atualiza um professor existente
     * @param id ID do professor
     * @param professorDados dados atualizados do professor
     * @return professor atualizado
     * @throws IllegalArgumentException se o professor não existe
     */
    public Professor atualizar(Integer id, Professor professorDados) {
        Professor professor = professorRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado com ID: " + id));
        
        professor.setNome(professorDados.getNome());
        professor.setEspecialidade(professorDados.getEspecialidade());
        
        return professorRepository.save(professor);
    }
    
    /**
     * Exclui um professor
     * @param id ID do professor
     * @throws IllegalArgumentException se o professor não existe
     */
    public void excluir(Integer id) {
        if (!professorRepository.existsById(id)) {
            throw new IllegalArgumentException("Professor não encontrado com ID: " + id);
        }
        professorRepository.deleteById(id);
    }
    
    /**
     * Busca professores por especialidade
     * @param especialidade especialidade do professor
     * @return lista de professores encontrados
     */
    @Transactional(readOnly = true)
    public List<Professor> buscarPorEspecialidade(String especialidade) {
        return professorRepository.findByEspecialidadeContainingIgnoreCase(especialidade);
    }
    
    /**
     * Busca professores por nome
     * @param nome nome do professor
     * @return lista de professores encontrados
     */
    @Transactional(readOnly = true)
    public List<Professor> buscarPorNome(String nome) {
        return professorRepository.findByNomeContainingIgnoreCase(nome);
    }
    
    /**
     * Verifica se um professor existe
     * @param id ID do professor
     * @return true se existe, false caso contrário
     */
    @Transactional(readOnly = true)
    public boolean existe(Integer id) {
        return professorRepository.existsById(id);
    }
}
