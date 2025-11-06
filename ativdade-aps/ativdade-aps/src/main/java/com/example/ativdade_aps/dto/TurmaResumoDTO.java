package com.example.ativdade_aps.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TurmaResumoDTO {
    private Long id;
    private String codigoTurma;
    private String curso;
    private String professor;
    private int quantidadeEstudantes;
}
