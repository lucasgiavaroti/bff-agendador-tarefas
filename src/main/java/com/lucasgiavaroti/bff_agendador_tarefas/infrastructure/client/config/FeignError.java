package com.lucasgiavaroti.bff_agendador_tarefas.infrastructure.client.config;

import com.lucasgiavaroti.bff_agendador_tarefas.infrastructure.exceptions.BusinessException;
import com.lucasgiavaroti.bff_agendador_tarefas.infrastructure.exceptions.ConflictException;
import com.lucasgiavaroti.bff_agendador_tarefas.infrastructure.exceptions.IllegalArgumentException;
import com.lucasgiavaroti.bff_agendador_tarefas.infrastructure.exceptions.NotFoundException;
import com.lucasgiavaroti.bff_agendador_tarefas.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FeignError implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {

        String mensagemErro = mensagemErro(response);

        switch (response.status()) {
            case 409 :
                return new ConflictException("Erro: " + mensagemErro);
            case 404 :
                return new NotFoundException("Erro: " + mensagemErro);
            case 401 :
                return new UnauthorizedException("Erro: " + mensagemErro);
            case 400 :
                return new IllegalArgumentException("Erro: " + mensagemErro);
            default:
                return new BusinessException("Erro: " + mensagemErro);
        }
    }

    private String mensagemErro(Response response){
        try{
            if(Objects.isNull(response.body())){
                return "";
            }
            return new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
