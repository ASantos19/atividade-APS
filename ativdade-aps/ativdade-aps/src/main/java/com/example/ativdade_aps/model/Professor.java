package com.example.ativdade_aps.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade Professor representa um professor universitário
 * Relacionamento OneToMany com Turma
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Professor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank(message = "Nome do professor é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    @Column(nullable = false)
    private String nome;
    
    @NotBlank(message = "Especialidade é obrigatória")
    @Size(min = 2, max = 50, message = "Especialidade deve ter entre 2 e 50 caracteres")
    @Column(nullable = false)
    private String especialidade;

    @JsonIgnoreProperties("professor")
    @OneToMany(mappedBy = "professor")
    private List<Turma> turmas;
}
