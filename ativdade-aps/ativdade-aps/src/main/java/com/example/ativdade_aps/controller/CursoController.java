package com.example.ativdade_aps.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ativdade_aps.model.Curso;
import com.example.ativdade_aps.service.CursoService;

import jakarta.validation.Valid;

/**
 * Controller REST para operações da entidade Curso
 */
@RestController
@RequestMapping("/api/cursos")
public class CursoController {
    
    @Autowired
    private CursoService cursoService;
    
    /**
     * Lista todos os cursos
     * GET /api/cursos
     */
    @GetMapping
    public ResponseEntity<List<Curso>> listarTodos() {
        List<Curso> cursos = cursoService.listarTodos();
        return ResponseEntity.ok(cursos);
    }
    
    /**
     * Busca um curso por ID
     * GET /api/cursos/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarPorId(@PathVariable Integer id) {
        Optional<Curso> curso = cursoService.buscarPorId(id);
        return curso.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Busca um curso por código
     * GET /api/cursos/codigo/{codigoCurso}
     */
    @GetMapping("/codigo/{codigoCurso}")
    public ResponseEntity<Curso> buscarPorCodigo(@PathVariable String codigoCurso) {
        Optional<Curso> curso = cursoService.buscarPorCodigo(codigoCurso);
        return curso.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Busca cursos por nome
     * GET /api/cursos/buscar?nome={nome}
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<Curso>> buscarPorNome(@RequestParam String nome) {
        List<Curso> cursos = cursoService.buscarPorNome(nome);
        return ResponseEntity.ok(cursos);
    }
    
    /**
     * Cria um novo curso
     * POST /api/cursos
     */
    @PostMapping
    public ResponseEntity<Curso> criar(@Valid @RequestBody Curso curso) {
        try {
            Curso cursoCriado = cursoService.criar(curso);
            return ResponseEntity.status(HttpStatus.CREATED).body(cursoCriado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * Atualiza um curso existente
     * PUT /api/cursos/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizar(@PathVariable Integer id, @Valid @RequestBody Curso curso) {
        try {
            Curso cursoAtualizado = cursoService.atualizar(id, curso);
            return ResponseEntity.ok(cursoAtualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Exclui um curso
     * DELETE /api/cursos/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        try {
            cursoService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
