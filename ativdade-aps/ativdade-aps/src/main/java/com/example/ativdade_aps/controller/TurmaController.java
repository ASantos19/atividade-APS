package com.example.ativdade_aps.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ativdade_aps.model.Turma;
import com.example.ativdade_aps.service.TurmaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/turmas")
@RequiredArgsConstructor
public class TurmaController {

    private final TurmaService turmaService;

    @GetMapping
    public ResponseEntity<List<Turma>> listar() {
        return ResponseEntity.ok(turmaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> buscarPorId(@PathVariable Long id) {
        Optional<Turma> turma = turmaService.buscarPorId(id);
        return turma.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Turma> criar(@RequestBody Turma turma) {
        return ResponseEntity.ok(turmaService.salvar(turma));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizar(@PathVariable Long id, @RequestBody Turma turma) {
        Optional<Turma> existente = turmaService.buscarPorId(id);
        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        turma.setId(id);
        return ResponseEntity.ok(turmaService.salvar(turma));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        turmaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
