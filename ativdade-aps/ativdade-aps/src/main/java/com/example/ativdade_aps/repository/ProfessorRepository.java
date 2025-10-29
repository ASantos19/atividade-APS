package com.example.ativdade_aps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ativdade_aps.model.Professor;

/**
 * Repository para operações de banco de dados da entidade Professor
 */
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
    
    /**
     * Busca professores por especialidade
     * @param especialidade especialidade do professor
     * @return lista de professores encontrados
     */
    java.util.List<Professor> findByEspecialidade(String especialidade);
    
    /**
     * Busca professores por nome (case insensitive)
     * @param nome nome do professor
     * @return lista de professores encontrados
     */
    java.util.List<Professor> findByNomeContainingIgnoreCase(String nome);
    
    /**
     * Busca professores por especialidade (case insensitive)
     * @param especialidade especialidade do professor
     * @return lista de professores encontrados
     */
    java.util.List<Professor> findByEspecialidadeContainingIgnoreCase(String especialidade);
}
