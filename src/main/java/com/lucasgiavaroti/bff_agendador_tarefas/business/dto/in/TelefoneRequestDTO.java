package com.lucasgiavaroti.bff_agendador_tarefas.business.dto.in;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TelefoneRequestDTO {

    private String numero;
    private String ddd;

}
