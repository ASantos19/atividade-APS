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

import com.example.ativdade_aps.model.Estudante;
import com.example.ativdade_aps.service.EstudanteService;

import jakarta.validation.Valid;

/**
 * Controller REST para operações da entidade Estudante
 */
@RestController
@RequestMapping("/api/estudantes")
public class EstudanteController {
    
    @Autowired
    private EstudanteService estudanteService;
    
    /**
     * Lista todos os estudantes
     * GET /api/estudantes
     */
    @GetMapping
    public ResponseEntity<List<Estudante>> listarTodos() {
        List<Estudante> estudantes = estudanteService.listarTodos();
        return ResponseEntity.ok(estudantes);
    }
    
    /**
     * Busca um estudante por ID
     * GET /api/estudantes/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Estudante> buscarPorId(@PathVariable Integer id) {
        Optional<Estudante> estudante = estudanteService.buscarPorId(id);
        return estudante.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Busca um estudante por matrícula
     * GET /api/estudantes/matricula/{matricula}
     */
    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<Estudante> buscarPorMatricula(@PathVariable Integer matricula) {
        Optional<Estudante> estudante = estudanteService.buscarPorMatricula(matricula);
        return estudante.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Busca estudantes por nome
     * GET /api/estudantes/buscar?nome={nome}
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<Estudante>> buscarPorNome(@RequestParam String nome) {
        List<Estudante> estudantes = estudanteService.buscarPorNome(nome);
        return ResponseEntity.ok(estudantes);
    }
    
    /**
     * Busca estudantes matriculados em uma turma específica
     * GET /api/estudantes/turma/{turmaId}
     */
    @GetMapping("/turma/{turmaId}")
    public ResponseEntity<List<Estudante>> buscarPorTurma(@PathVariable Integer turmaId) {
        List<Estudante> estudantes = estudanteService.buscarPorTurma(turmaId);
        return ResponseEntity.ok(estudantes);
    }
    
    /**
     * Busca estudantes que não estão matriculados em nenhuma turma
     * GET /api/estudantes/sem-turma
     */
    @GetMapping("/sem-turma")
    public ResponseEntity<List<Estudante>> buscarEstudantesSemTurma() {
        List<Estudante> estudantes = estudanteService.buscarEstudantesSemTurma();
        return ResponseEntity.ok(estudantes);
    }
    
    /**
     * Conta o número de turmas de um estudante
     * GET /api/estudantes/{id}/contar-turmas
     */
    @GetMapping("/{id}/contar-turmas")
    public ResponseEntity<Integer> contarTurmas(@PathVariable Integer id) {
        Integer quantidade = estudanteService.contarTurmas(id);
        return ResponseEntity.ok(quantidade);
    }
    
    /**
     * Cria um novo estudante
     * POST /api/estudantes
     */
    @PostMapping
    public ResponseEntity<Estudante> criar(@Valid @RequestBody Estudante estudante) {
        try {
            Estudante estudanteCriado = estudanteService.criar(estudante);
            return ResponseEntity.status(HttpStatus.CREATED).body(estudanteCriado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * Atualiza um estudante existente
     * PUT /api/estudantes/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Estudante> atualizar(@PathVariable Integer id, @Valid @RequestBody Estudante estudante) {
        try {
            Estudante estudanteAtualizado = estudanteService.atualizar(id, estudante);
            return ResponseEntity.ok(estudanteAtualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Exclui um estudante
     * DELETE /api/estudantes/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        try {
            estudanteService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
