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

import com.example.ativdade_aps.model.Professor;
import com.example.ativdade_aps.service.ProfessorService;

import jakarta.validation.Valid;

/**
 * Controller REST para operações da entidade Professor
 */
@RestController
@RequestMapping("/api/professores")
public class ProfessorController {
    
    @Autowired
    private ProfessorService professorService;
    
    /**
     * Lista todos os professores
     * GET /api/professores
     */
    @GetMapping
    public ResponseEntity<List<Professor>> listarTodos() {
        List<Professor> professores = professorService.listarTodos();
        return ResponseEntity.ok(professores);
    }
    
    /**
     * Busca um professor por ID
     * GET /api/professores/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscarPorId(@PathVariable Integer id) {
        Optional<Professor> professor = professorService.buscarPorId(id);
        return professor.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Busca professores por especialidade
     * GET /api/professores/especialidade/{especialidade}
     */
    @GetMapping("/especialidade/{especialidade}")
    public ResponseEntity<List<Professor>> buscarPorEspecialidade(@PathVariable String especialidade) {
        List<Professor> professores = professorService.buscarPorEspecialidade(especialidade);
        return ResponseEntity.ok(professores);
    }
    
    /**
     * Busca professores por nome
     * GET /api/professores/buscar?nome={nome}
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<Professor>> buscarPorNome(@RequestParam String nome) {
        List<Professor> professores = professorService.buscarPorNome(nome);
        return ResponseEntity.ok(professores);
    }
    
    /**
     * Cria um novo professor
     * POST /api/professores
     */
    @PostMapping
    public ResponseEntity<Professor> criar(@Valid @RequestBody Professor professor) {
        Professor professorCriado = professorService.criar(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(professorCriado);
    }
    
    /**
     * Atualiza um professor existente
     * PUT /api/professores/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizar(@PathVariable Integer id, @Valid @RequestBody Professor professor) {
        try {
            Professor professorAtualizado = professorService.atualizar(id, professor);
            return ResponseEntity.ok(professorAtualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Exclui um professor
     * DELETE /api/professores/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        try {
            professorService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
