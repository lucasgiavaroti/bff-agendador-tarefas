package com.lucasgiavaroti.bff_agendador_tarefas.business.dto.in;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRequestDTO {
    
    private String nome;
    private String email;
    private String senha;
    private List<EnderecoRequestDTO> endereco;
    private List<TelefoneRequestDTO> telefone;

}
