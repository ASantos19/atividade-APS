package com.example.ativdade_aps.dto;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TurmaDetalhadoDTO {
    private Long id;
    private String codigoTurma;
    private String horario;
    private String curso; 
    private String professor; 
    private List<String> estudantes; 
}
