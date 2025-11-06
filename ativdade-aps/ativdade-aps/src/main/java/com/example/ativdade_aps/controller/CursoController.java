package com.example.ativdade_aps.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ativdade_aps.model.Curso;
import com.example.ativdade_aps.service.CursoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> listar() {
        return ResponseEntity.ok(cursoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarPorId(@PathVariable Long id) {
        Optional<Curso> curso = cursoService.buscarPorId(id);
        return curso.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Curso> criar(@RequestBody Curso curso) {
        return ResponseEntity.ok(cursoService.salvar(curso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizar(@PathVariable Long id, @RequestBody Curso curso) {
        Optional<Curso> existente = cursoService.buscarPorId(id);
        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        curso.setId(id);
        return ResponseEntity.ok(cursoService.salvar(curso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        cursoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

