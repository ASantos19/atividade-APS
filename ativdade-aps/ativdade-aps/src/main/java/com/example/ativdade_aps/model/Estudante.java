package com.example.ativdade_aps.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade Estudante representa um estudante universitário
 * Relacionamento ManyToMany com Turma
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estudante {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank(message = "Nome do estudante é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    @Column(nullable = false)
    private String nome;
    
    @NotNull(message = "Matrícula é obrigatória")
    @Column(unique = true, nullable = false)
    private Integer matricula;

    @ManyToMany(mappedBy = "estudantes")
    @JsonIgnoreProperties("estudantes")
    private List<Turma> turmas;
}
