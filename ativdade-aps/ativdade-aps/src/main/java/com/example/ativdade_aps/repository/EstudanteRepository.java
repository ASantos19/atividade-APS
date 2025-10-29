package com.example.ativdade_aps.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ativdade_aps.model.Estudante;

/**
 * Repository para operações de banco de dados da entidade Estudante
 */
@Repository
public interface EstudanteRepository extends JpaRepository<Estudante, Integer> {
    
    /**
     * Busca um estudante pela matrícula única
     * @param matricula matrícula do estudante
     * @return Optional contendo o estudante se encontrado
     */
    Optional<Estudante> findByMatricula(Integer matricula);
    
    /**
     * Verifica se existe um estudante com a matrícula informada
     * @param matricula matrícula do estudante
     * @return true se existe, false caso contrário
     */
    boolean existsByMatricula(Integer matricula);
    
    /**
     * Busca estudantes por nome (case insensitive)
     * @param nome nome do estudante
     * @return lista de estudantes encontrados
     */
    java.util.List<Estudante> findByNomeContainingIgnoreCase(String nome);
    
    /**
     * Busca estudantes matriculados em uma turma específica
     * @param turmaId ID da turma
     * @return lista de estudantes da turma
     */
    @Query("SELECT e FROM Estudante e JOIN e.turmas t WHERE t.id = :turmaId")
    java.util.List<Estudante> findByTurmaId(@Param("turmaId") Integer turmaId);
    
    /**
     * Busca estudantes que não estão matriculados em nenhuma turma
     * @return lista de estudantes sem turma
     */
    @Query("SELECT e FROM Estudante e WHERE SIZE(e.turmas) = 0")
    java.util.List<Estudante> findEstudantesSemTurma();
    
    /**
     * Conta o número de turmas de um estudante
     * @param estudanteId ID do estudante
     * @return número de turmas
     */
    @Query("SELECT SIZE(e.turmas) FROM Estudante e WHERE e.id = :estudanteId")
    Integer countTurmasByEstudanteId(@Param("estudanteId") Integer estudanteId);
}
