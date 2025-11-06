package com.example.ativdade_aps.model;


import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String especialidade;

    @JsonIgnoreProperties("responsavel")
    @OneToMany(mappedBy = "responsavel")
    private List<Turma> turmas;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }

    public List<Turma> getTurmas() { return turmas; }
    public void setTurmas(List<Turma> turmas) { this.turmas = turmas; }
}
