package com.lucasgiavaroti.bff_agendador_tarefas.infrastructure.client;

import com.lucasgiavaroti.bff_agendador_tarefas.business.dto.in.EmailRequestoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificacao", url = "${notificacao.url}")
public interface NotificacaoClient {

    @PostMapping
    void enviarEmail(@RequestBody EmailRequestoDTO emailRequestoDTO);

}
