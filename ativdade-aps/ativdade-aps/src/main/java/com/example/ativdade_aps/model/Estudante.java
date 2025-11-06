package com.example.ativdade_aps.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class Estudante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer matricula;

    @JsonIgnoreProperties("estudantes")
    @ManyToMany(mappedBy = "estudantes")
    private List<Turma> turmas;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Integer getMatricula() { return matricula; }
    public void setMatricula(Integer matricula) { this.matricula = matricula; }

    public List<Turma> getTurmas() { return turmas; }
    public void setTurmas(List<Turma> turmas) { this.turmas = turmas; }
}
