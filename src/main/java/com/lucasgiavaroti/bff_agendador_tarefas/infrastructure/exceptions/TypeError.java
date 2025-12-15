package com.lucasgiavaroti.bff_agendador_tarefas.infrastructure.exceptions;

import java.time.LocalDateTime;

public record TypeError(
        Integer status,
        String titulo,
        String detalhe,
        LocalDateTime timestamp
) {
}
