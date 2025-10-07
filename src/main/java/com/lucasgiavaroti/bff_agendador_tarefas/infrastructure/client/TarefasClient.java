package com.lucasgiavaroti.bff_agendador_tarefas.infrastructure.client;

import com.lucasgiavaroti.bff_agendador_tarefas.business.dto.in.TarefaRequestDTO;
import com.lucasgiavaroti.bff_agendador_tarefas.business.dto.out.TarefaDTO;
import com.lucasgiavaroti.bff_agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${agendador.tarefas.url}")
public interface TarefasClient {

    @PostMapping
    TarefaDTO salvarTarefa(@RequestBody TarefaRequestDTO tarefaDTO, @RequestHeader( "Authorization") String token);

    @GetMapping("/eventos")
     List<TarefaDTO> buscaTarefasAgendadasPorPeriodo(
            @RequestParam("data_inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam("data_fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim,
            @RequestHeader( "Authorization") String token);

    @GetMapping
    List<TarefaDTO> buscaTarefasPorEmailUsuario(@RequestHeader( "Authorization") String token);

    @PutMapping("/{id}")
    TarefaDTO atualizaDadosTarefa(@RequestBody TarefaRequestDTO tarefaDTO, @PathVariable String id, @RequestHeader( "Authorization") String token);

    @DeleteMapping("/{id}")
    void deletarTarefa(@RequestHeader( "Authorization") String token, @PathVariable String id);

    @PatchMapping("/{id}")
    TarefaDTO alterarStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status, @PathVariable String id, @RequestHeader( "Authorization") String token);
}
