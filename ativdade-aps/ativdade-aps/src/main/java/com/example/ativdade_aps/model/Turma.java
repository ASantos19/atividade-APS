package com.example.ativdade_aps.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String codigoTurma;

    private String horario;

    @JsonIgnoreProperties("turmas")
    @ManyToMany
    @JoinTable(
        name = "turma_estudante",
        joinColumns = @JoinColumn(name = "turma_id"),
        inverseJoinColumns = @JoinColumn(name = "estudante_id")
    )
    private List<Estudante> estudantes;

    @JsonIgnoreProperties("turmas")
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @JsonIgnoreProperties("turmas")
    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor responsavel;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigoTurma() { return codigoTurma; }
    public void setCodigoTurma(String codigoTurma) { this.codigoTurma = codigoTurma; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    public List<Estudante> getEstudantes() { return estudantes; }
    public void setEstudantes(List<Estudante> estudantes) { this.estudantes = estudantes; }

    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }

    public Professor getResponsavel() { return responsavel; }
    public void setResponsavel(Professor responsavel) { this.responsavel = responsavel; }
}
