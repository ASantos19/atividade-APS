package com.example.ativdade_aps.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade Turma representa uma turma universitária
 * Relacionamentos: ManyToOne com Curso e Professor, ManyToMany com Estudante
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Turma {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank(message = "Código da turma é obrigatório")
    @Size(min = 3, max = 10, message = "Código deve ter entre 3 e 10 caracteres")
    @Column(unique = true, nullable = false)
    private String codigoTurma;
    
    @NotBlank(message = "Horário é obrigatório")
    @Size(min = 5, max = 20, message = "Horário deve ter entre 5 e 20 caracteres")
    @Column(nullable = false)
    private String horario;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    @JsonIgnoreProperties("turmas")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    @JsonIgnoreProperties("turmas")
    private Professor professor;

    @ManyToMany
    @JoinTable(
        name = "turma_estudante",
        joinColumns = @JoinColumn(name = "turma_id"),
        inverseJoinColumns = @JoinColumn(name = "estudante_id")
    )
    @JsonIgnoreProperties("turmas")
    private List<Estudante> estudantes;
}
