package com.lucasgiavaroti.bff_agendador_tarefas.infrastructure.client.config;

import com.lucasgiavaroti.bff_agendador_tarefas.infrastructure.exceptions.BusinessException;
import com.lucasgiavaroti.bff_agendador_tarefas.infrastructure.exceptions.ConflictException;
import com.lucasgiavaroti.bff_agendador_tarefas.infrastructure.exceptions.NotFoundException;
import com.lucasgiavaroti.bff_agendador_tarefas.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignError implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()) {
            case 409 :
                return new ConflictException("Atributo já existente");
            case 404 :
                return new NotFoundException("Atributo não encontrado");
            case 401 :
                return new UnauthorizedException("Usuário não autenticado");
            default:
                return new BusinessException("Erro interno de servidor");
        }
    }
}
