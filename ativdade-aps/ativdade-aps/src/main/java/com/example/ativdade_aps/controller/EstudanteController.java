package com.example.ativdade_aps.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ativdade_aps.model.Estudante;
import com.example.ativdade_aps.service.EstudanteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/estudantes")
@RequiredArgsConstructor
public class EstudanteController {

    private final EstudanteService estudanteService;

    @GetMapping
    public ResponseEntity<List<Estudante>> listar() {
        return ResponseEntity.ok(estudanteService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudante> buscarPorId(@PathVariable Long id) {
        Optional<Estudante> estudante = estudanteService.buscarPorId(id);
        return estudante.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Estudante> criar(@RequestBody Estudante estudante) {
        Estudante salvo = estudanteService.salvar(estudante);
        return ResponseEntity.ok(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudante> atualizar(@PathVariable Long id, @RequestBody Estudante estudante) {
        Optional<Estudante> existente = estudanteService.buscarPorId(id);
        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        estudante.setId(id);
        return ResponseEntity.ok(estudanteService.salvar(estudante));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        estudanteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
