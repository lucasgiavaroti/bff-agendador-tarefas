package com.lucasgiavaroti.bff_agendador_tarefas.business;

import com.lucasgiavaroti.bff_agendador_tarefas.business.dto.in.EmailRequestoDTO;
import com.lucasgiavaroti.bff_agendador_tarefas.infrastructure.client.NotificacaoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificacaoService {

    private final NotificacaoClient notificacaoClient;

    public void enviarEmail(EmailRequestoDTO emailRequestoDTO){
        notificacaoClient.enviarEmail(emailRequestoDTO);
    }

}
