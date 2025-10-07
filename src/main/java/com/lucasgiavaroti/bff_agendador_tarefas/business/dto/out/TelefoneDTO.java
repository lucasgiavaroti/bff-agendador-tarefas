package com.lucasgiavaroti.bff_agendador_tarefas.business.dto.out;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TelefoneDTO {

    private Long id;
    private String numero;
    private String ddd;

}
