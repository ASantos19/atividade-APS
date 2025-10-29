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
import org.springframework.web.bind.annotation.RestController;

import com.example.ativdade_aps.model.Turma;
import com.example.ativdade_aps.service.TurmaService;

import jakarta.validation.Valid;

/**
 * Controller REST para operações da entidade Turma
 */
@RestController
@RequestMapping("/api/turmas")
public class TurmaController {
    
    @Autowired
    private TurmaService turmaService;
    
    /**
     * Lista todas as turmas
     * GET /api/turmas
     */
    @GetMapping
    public ResponseEntity<List<Turma>> listarTodos() {
        List<Turma> turmas = turmaService.listarTodos();
        return ResponseEntity.ok(turmas);
    }
    
    /**
     * Busca uma turma por ID
     * GET /api/turmas/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Turma> buscarPorId(@PathVariable Integer id) {
        Optional<Turma> turma = turmaService.buscarPorId(id);
        return turma.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Busca uma turma por código
     * GET /api/turmas/codigo/{codigoTurma}
     */
    @GetMapping("/codigo/{codigoTurma}")
    public ResponseEntity<Turma> buscarPorCodigo(@PathVariable String codigoTurma) {
        Optional<Turma> turma = turmaService.buscarPorCodigo(codigoTurma);
        return turma.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Busca turmas por curso
     * GET /api/turmas/curso/{cursoId}
     */
    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<Turma>> buscarPorCurso(@PathVariable Integer cursoId) {
        List<Turma> turmas = turmaService.buscarPorCurso(cursoId);
        return ResponseEntity.ok(turmas);
    }
    
    /**
     * Busca turmas por professor
     * GET /api/turmas/professor/{professorId}
     */
    @GetMapping("/professor/{professorId}")
    public ResponseEntity<List<Turma>> buscarPorProfessor(@PathVariable Integer professorId) {
        List<Turma> turmas = turmaService.buscarPorProfessor(professorId);
        return ResponseEntity.ok(turmas);
    }
    
    /**
     * Busca turmas por horário
     * GET /api/turmas/horario/{horario}
     */
    @GetMapping("/horario/{horario}")
    public ResponseEntity<List<Turma>> buscarPorHorario(@PathVariable String horario) {
        List<Turma> turmas = turmaService.buscarPorHorario(horario);
        return ResponseEntity.ok(turmas);
    }
    
    /**
     * Busca turmas com estudantes matriculados
     * GET /api/turmas/com-estudantes
     */
    @GetMapping("/com-estudantes")
    public ResponseEntity<List<Turma>> buscarTurmasComEstudantes() {
        List<Turma> turmas = turmaService.buscarTurmasComEstudantes();
        return ResponseEntity.ok(turmas);
    }
    
    /**
     * Conta o número de estudantes em uma turma
     * GET /api/turmas/{id}/contar-estudantes
     */
    @GetMapping("/{id}/contar-estudantes")
    public ResponseEntity<Integer> contarEstudantes(@PathVariable Integer id) {
        Integer quantidade = turmaService.contarEstudantes(id);
        return ResponseEntity.ok(quantidade);
    }
    
    /**
     * Cria uma nova turma
     * POST /api/turmas
     */
    @PostMapping
    public ResponseEntity<Turma> criar(@Valid @RequestBody Turma turma) {
        try {
            Turma turmaCriada = turmaService.criar(turma);
            return ResponseEntity.status(HttpStatus.CREATED).body(turmaCriada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * Atualiza uma turma existente
     * PUT /api/turmas/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizar(@PathVariable Integer id, @Valid @RequestBody Turma turma) {
        try {
            Turma turmaAtualizada = turmaService.atualizar(id, turma);
            return ResponseEntity.ok(turmaAtualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Exclui uma turma
     * DELETE /api/turmas/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        try {
            turmaService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
