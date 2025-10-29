package com.example.ativdade_aps.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ativdade_aps.model.Curso;
import com.example.ativdade_aps.repository.CursoRepository;

/**
 * Service para operações de negócio da entidade Curso
 */
@Service
@Transactional
public class CursoService {
    
    @Autowired
    private CursoRepository cursoRepository;
    
    /**
     * Lista todos os cursos
     * @return lista de todos os cursos
     */
    @Transactional(readOnly = true)
    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }
    
    /**
     * Busca um curso por ID
     * @param id ID do curso
     * @return Optional contendo o curso se encontrado
     */
    @Transactional(readOnly = true)
    public Optional<Curso> buscarPorId(Integer id) {
        return cursoRepository.findById(id);
    }
    
    /**
     * Busca um curso por código
     * @param codigoCurso código do curso
     * @return Optional contendo o curso se encontrado
     */
    @Transactional(readOnly = true)
    public Optional<Curso> buscarPorCodigo(String codigoCurso) {
        return cursoRepository.findByCodigoCurso(codigoCurso);
    }
    
    /**
     * Cria um novo curso
     * @param curso curso a ser criado
     * @return curso criado
     * @throws IllegalArgumentException se o código já existe
     */
    public Curso criar(Curso curso) {
        if (cursoRepository.existsByCodigoCurso(curso.getCodigoCurso())) {
            throw new IllegalArgumentException("Já existe um curso com o código: " + curso.getCodigoCurso());
        }
        return cursoRepository.save(curso);
    }
    
    /**
     * Atualiza um curso existente
     * @param id ID do curso
     * @param cursoDados dados atualizados do curso
     * @return curso atualizado
     * @throws IllegalArgumentException se o curso não existe ou código já existe
     */
    public Curso atualizar(Integer id, Curso cursoDados) {
        Curso curso = cursoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado com ID: " + id));
        
        // Verifica se o código está sendo alterado e se já existe
        if (!curso.getCodigoCurso().equals(cursoDados.getCodigoCurso()) && 
            cursoRepository.existsByCodigoCurso(cursoDados.getCodigoCurso())) {
            throw new IllegalArgumentException("Já existe um curso com o código: " + cursoDados.getCodigoCurso());
        }
        
        curso.setNome(cursoDados.getNome());
        curso.setCodigoCurso(cursoDados.getCodigoCurso());
        curso.setEspecialidade(cursoDados.getEspecialidade());
        
        return cursoRepository.save(curso);
    }
    
    /**
     * Exclui um curso
     * @param id ID do curso
     * @throws IllegalArgumentException se o curso não existe
     */
    public void excluir(Integer id) {
        if (!cursoRepository.existsById(id)) {
            throw new IllegalArgumentException("Curso não encontrado com ID: " + id);
        }
        cursoRepository.deleteById(id);
    }
    
    /**
     * Busca cursos por nome
     * @param nome nome do curso
     * @return lista de cursos encontrados
     */
    @Transactional(readOnly = true)
    public List<Curso> buscarPorNome(String nome) {
        return cursoRepository.findByNomeContainingIgnoreCase(nome);
    }
    
    /**
     * Verifica se um curso existe
     * @param id ID do curso
     * @return true se existe, false caso contrário
     */
    @Transactional(readOnly = true)
    public boolean existe(Integer id) {
        return cursoRepository.existsById(id);
    }
}
