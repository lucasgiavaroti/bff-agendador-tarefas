package com.lucasgiavaroti.bff_agendador_tarefas.controller;

import com.lucasgiavaroti.bff_agendador_tarefas.infrastructure.exceptions.*;
import com.lucasgiavaroti.bff_agendador_tarefas.infrastructure.exceptions.IllegalArgumentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<TypeError> handleNotFoundException(NotFoundException ex){
        TypeError typeError = new TypeError(404, "Recurso não encontrado", ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(typeError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<TypeError> handleConflictException(ConflictException ex){
        TypeError typeError = new TypeError(409, "Recurso com conflito", ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(typeError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<TypeError> handleUnauthorizedException(UnauthorizedException ex){
        TypeError typeError = new TypeError(401, "Não autorizado", ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(typeError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<TypeError> handleBusinessException(BusinessException ex){
        TypeError typeError = new TypeError(500, "Erro interno de servidor", ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(typeError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<TypeError> handleIllegalArgumentException(IllegalArgumentException ex){
        TypeError typeError = new TypeError(400, "Requisição mal-formatada", ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(typeError, HttpStatus.BAD_REQUEST);
    }

}
