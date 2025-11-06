package com.example.ativdade_aps.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String codigoCurso;

    @JsonIgnoreProperties("curso")
    @OneToMany(mappedBy = "curso")
    private List<Turma> turmas;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCodigoCurso() { return codigoCurso; }
    public void setCodigoCurso(String codigoCurso) { this.codigoCurso = codigoCurso; }

    public List<Turma> getTurmas() { return turmas; }
    public void setTurmas(List<Turma> turmas) { this.turmas = turmas; }
}
