package com.example.ativdade_aps.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.ativdade_aps.dto.TurmaResumoDTO;
import com.example.ativdade_aps.model.Turma;
import com.example.ativdade_aps.service.TurmaService;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    // 🔹 Listar todas as turmas
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<Turma>> listar() {
        List<Turma> turmas = turmaService.listar();
        return ResponseEntity.ok(turmas); // Retorna 200 OK
    }

    // 🔹 Buscar turma por ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Turma> buscarPorId(@PathVariable Long id) {
        return turmaService.buscarPorId(id)
                .map(ResponseEntity::ok) // Retorna 200 OK se encontrado
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // Retorna 404 se não encontrado
    }

    // 🔹 Listar turmas resumidas (DTO)
    @GetMapping("/resumo")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<TurmaResumoDTO>> listarTurmasResumidas() {
        List<TurmaResumoDTO> turmasDTO = turmaService.listar().stream()
                .map(turma -> {
                    TurmaResumoDTO dto = new TurmaResumoDTO();
                    dto.setId(turma.getId());
                    dto.setCodigoTurma(turma.getCodigoTurma());
                    dto.setCurso(turma.getCurso() != null ? turma.getCurso().getNome() : null);
                    dto.setProfessor(turma.getResponsavel() != null ? turma.getResponsavel().getNome() : null);
                    dto.setQuantidadeEstudantes(
                            turma.getEstudantes() != null ? turma.getEstudantes().size() : 0);
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(turmasDTO); // Retorna 200 OK
    }

    // 🔹 Criar nova turma
    @PostMapping
    public ResponseEntity<Turma> criar(@RequestBody Turma turma) {
        Turma novaTurma = turmaService.salvar(turma);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTurma); // Retorna 201 Created
    }

    // 🔹 Atualizar turma existente
    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizar(@PathVariable Long id, @RequestBody Turma turma) {
        if (!turmaService.buscarPorId(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 se não existir
        }
        turma.setId(id);
        Turma turmaAtualizada = turmaService.salvar(turma);
        return ResponseEntity.ok(turmaAtualizada); // Retorna 200 OK
    }

    // 🔹 Deletar turma por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!turmaService.buscarPorId(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 se não existir
        }
        turmaService.deletar(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }
}
