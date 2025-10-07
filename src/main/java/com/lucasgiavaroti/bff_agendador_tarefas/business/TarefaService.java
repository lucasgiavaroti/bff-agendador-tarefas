package com.lucasgiavaroti.bff_agendador_tarefas.business;

import com.lucasgiavaroti.bff_agendador_tarefas.business.dto.in.TarefaRequestDTO;
import com.lucasgiavaroti.bff_agendador_tarefas.business.dto.out.TarefaDTO;
import com.lucasgiavaroti.bff_agendador_tarefas.infrastructure.client.TarefasClient;
import com.lucasgiavaroti.bff_agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

   private final TarefasClient tarefasClient;

    public TarefaDTO salvarTarefa(TarefaRequestDTO tarefaDTO, String token){
        return tarefasClient.salvarTarefa(tarefaDTO, token);
    }

    public List<TarefaDTO> buscaTarefasAgendadasPorPeriodo (LocalDateTime dataInicio, LocalDateTime dataFim, String token){
        return tarefasClient.buscaTarefasAgendadasPorPeriodo(dataInicio, dataFim, token);
    }

    public List<TarefaDTO> buscaTarefasPorEmailUsuario(String token){
        return tarefasClient.buscaTarefasPorEmailUsuario(token);
    }

    public TarefaDTO atualizaDadosTarefa(TarefaRequestDTO tarefaDTO, String id, String token){
        return tarefasClient.atualizaDadosTarefa(tarefaDTO, id, token);
    }

    public void deletarTarefa(String id, String token){
        tarefasClient.deletarTarefa(token, id);
    }

    public TarefaDTO alterarStatus(StatusNotificacaoEnum status, String id, String token) {
        return tarefasClient.alterarStatusNotificacao(status, id, token);
    }
}
