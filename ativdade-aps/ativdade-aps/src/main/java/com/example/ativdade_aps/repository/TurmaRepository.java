package com.example.ativdade_aps.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ativdade_aps.model.Turma;

/**
 * Repository para operações de banco de dados da entidade Turma
 */
@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer> {
    
    /**
     * Busca uma turma pelo código único
     * @param codigoTurma código da turma
     * @return Optional contendo a turma se encontrada
     */
    Optional<Turma> findByCodigoTurma(String codigoTurma);
    
    /**
     * Verifica se existe uma turma com o código informado
     * @param codigoTurma código da turma
     * @return true se existe, false caso contrário
     */
    boolean existsByCodigoTurma(String codigoTurma);
    
    /**
     * Busca turmas por curso
     * @param cursoId ID do curso
     * @return lista de turmas do curso
     */
    java.util.List<Turma> findByCursoId(Integer cursoId);
    
    /**
     * Busca turmas por professor
     * @param professorId ID do professor
     * @return lista de turmas do professor
     */
    java.util.List<Turma> findByProfessorId(Integer professorId);
    
    /**
     * Busca turmas por horário
     * @param horario horário da turma
     * @return lista de turmas no horário
     */
    java.util.List<Turma> findByHorario(String horario);
    
    /**
     * Busca turmas com estudantes matriculados
     * @return lista de turmas com estudantes
     */
    @Query("SELECT t FROM Turma t WHERE SIZE(t.estudantes) > 0")
    java.util.List<Turma> findTurmasComEstudantes();
    
    /**
     * Conta o número de estudantes em uma turma
     * @param turmaId ID da turma
     * @return número de estudantes
     */
    @Query("SELECT SIZE(t.estudantes) FROM Turma t WHERE t.id = :turmaId")
    Integer countEstudantesByTurmaId(@Param("turmaId") Integer turmaId);
}
