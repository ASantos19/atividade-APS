package com.example.ativdade_aps.dto;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NovaTurmaDTO {
    private String codigoTurma;
    private String horario;
    private Long cursoId; // referência à entidade Curso
    private Long professorId; // referência ao Professor responsável
    private List<Long> estudanteIds; // lista de estudantes matriculados
}
