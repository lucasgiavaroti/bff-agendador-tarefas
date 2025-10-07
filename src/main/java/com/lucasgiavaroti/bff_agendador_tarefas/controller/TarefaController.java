package com.lucasgiavaroti.bff_agendador_tarefas.controller;

import com.lucasgiavaroti.bff_agendador_tarefas.business.TarefaService;
import com.lucasgiavaroti.bff_agendador_tarefas.business.dto.in.TarefaRequestDTO;
import com.lucasgiavaroti.bff_agendador_tarefas.business.dto.out.TarefaDTO;
import com.lucasgiavaroti.bff_agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.lucasgiavaroti.bff_agendador_tarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefa")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastro de tarefas de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    @Operation(summary = "Salvar tarefas",description = "Cria uma nova tarefa no sistema")
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno de servidor")
    public ResponseEntity<TarefaDTO> salvarTarefa(@RequestBody TarefaRequestDTO tarefaDTO, @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefaService.salvarTarefa(tarefaDTO, token));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca tarefas",description = "Buscar tarefas cadastradas por período")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno de servidor")
    public ResponseEntity<List<TarefaDTO>> buscaTarefasAgendadasPorPeriodo(
            @RequestParam("data_inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam("data_fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim,
            @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefaService.buscaTarefasAgendadasPorPeriodo(dataInicio, dataFim, token));
    }

    @GetMapping
    @Operation(summary = "Busca lista tarefas por e-mail",description = "Buscar tarefas cadastradas por e-mail de usuário")
    @ApiResponse(responseCode = "200", description = "Tarefas buscadas com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno de servidor")
    @ApiResponse(responseCode = "403", description = "Email não encontrado")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<List<TarefaDTO>> buscaTarefasPorEmailUsuario(@RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefaService.buscaTarefasPorEmailUsuario(token));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Dados da Tarefa do Usuário",description = "Atualizar dados da tarefa no sistema")
    @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno de servidor")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<TarefaDTO> atualizaDadosTarefa(@RequestBody TarefaRequestDTO tarefaDTO, @PathVariable String id, @RequestHeader(name = "Authorization", required = false) String token){
        return  ResponseEntity.ok(tarefaService.atualizaDadosTarefa(tarefaDTO, id, token));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar tarefa",description = "Deleta uma tarefa por id no sistema")
    @ApiResponse(responseCode = "200", description = "Tarefa deletada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno de servidor")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<Void> deletarTarefa(@PathVariable String id, @RequestHeader(name = "Authorization", required = false) String token){
        tarefaService.deletarTarefa(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Atualizar status da tarefa",description = "Atualizar status de notificação da tarefa no sistema")
    @ApiResponse(responseCode = "200", description = "Status atualizado com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno de servidor")
    @ApiResponse(responseCode = "403", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<TarefaDTO> alterarStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status, @PathVariable String id, @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefaService.alterarStatus(status, id, token));
    }

}
