package com.example.ativdade_aps.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ativdade_aps.model.Curso;

/**
 * Repository para operações de banco de dados da entidade Curso
 */
@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
    
    /**
     * Busca um curso pelo código único
     * @param codigoCurso código do curso
     * @return Optional contendo o curso se encontrado
     */
    Optional<Curso> findByCodigoCurso(String codigoCurso);
    
    /**
     * Verifica se existe um curso com o código informado
     * @param codigoCurso código do curso
     * @return true se existe, false caso contrário
     */
    boolean existsByCodigoCurso(String codigoCurso);
    
    /**
     * Busca cursos por nome (case insensitive)
     * @param nome nome do curso
     * @return lista de cursos encontrados
     */
    java.util.List<Curso> findByNomeContainingIgnoreCase(String nome);
}
