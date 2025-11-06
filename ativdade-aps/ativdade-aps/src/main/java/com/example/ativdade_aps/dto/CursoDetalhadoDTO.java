package com.example.ativdade_aps.dto;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CursoDetalhadoDTO {
    private Long id;
    private String nome;
    private String codigoCurso;
    private List<String> turmas; 
}
