package com.example.ativdade_aps.dto;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfessorDetalhadoDTO {
    private Long id;
    private String nome;
    private String especialidade;
    private List<String> turmas; // nomes ou códigos das turmas ministradas
}
