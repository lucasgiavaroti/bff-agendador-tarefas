package com.lucasgiavaroti.bff_agendador_tarefas.business.dto.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucasgiavaroti.bff_agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefaRequestDTO {


    private String nomeTarefa;

    private String descricao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataAgendamento;

}
