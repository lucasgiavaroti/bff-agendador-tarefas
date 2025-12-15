package com.lucasgiavaroti.bff_agendador_tarefas.business.dto.out;

public record ViaCepDTO(String cep,
                        String logradouro,
                        String complemento,
                        String unidade,
                        String bairro,
                        String localidade,
                        String uf,
                        String estado,
                        String regiao,
                        String ibge,
                        String gia,
                        String ddd,
                        String siafi) {
}
