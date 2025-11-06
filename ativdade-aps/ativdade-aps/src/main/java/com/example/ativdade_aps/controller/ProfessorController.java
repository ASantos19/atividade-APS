package com.example.ativdade_aps.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ativdade_aps.model.Professor;
import com.example.ativdade_aps.service.ProfessorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/professores")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService professorService;

    @GetMapping
    public ResponseEntity<List<Professor>> listar() {
        return ResponseEntity.ok(professorService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscarPorId(@PathVariable Long id) {
        Optional<Professor> professor = professorService.buscarPorId(id);
        return professor.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Professor> criar(@RequestBody Professor professor) {
        return ResponseEntity.ok(professorService.salvar(professor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizar(@PathVariable Long id, @RequestBody Professor professor) {
        Optional<Professor> existente = professorService.buscarPorId(id);
        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        professor.setId(id);
        return ResponseEntity.ok(professorService.salvar(professor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        professorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
