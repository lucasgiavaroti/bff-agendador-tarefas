package com.lucasgiavaroti.bff_agendador_tarefas.infrastructure.exceptions;

public class BusinessException extends RuntimeException{
    public BusinessException(String msg) {
        super(msg);
    }
    public BusinessException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
