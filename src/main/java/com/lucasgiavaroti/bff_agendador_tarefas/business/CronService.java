package com.lucasgiavaroti.bff_agendador_tarefas.business;

import com.lucasgiavaroti.bff_agendador_tarefas.business.dto.in.EmailRequestoDTO;
import com.lucasgiavaroti.bff_agendador_tarefas.business.dto.in.LoginRequestDTO;
import com.lucasgiavaroti.bff_agendador_tarefas.business.dto.out.TarefaDTO;
import com.lucasgiavaroti.bff_agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TarefaService tarefaService;
    private final NotificacaoService notificacaoService;
    private final UsuarioService usuarioService;

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String adminPassword;

    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasProximaHora(){
        log.info("Iniciando o login como admin utilizando o e-mail: " + adminEmail + " e a senha: " + adminPassword);
        String token = login(getAdminLogin());
        log.info("Iniciando busca de tarefas");

        LocalDateTime horaFutura =  LocalDateTime.now().plusHours(1);
        LocalDateTime horaFuturaMaisCinco = LocalDateTime.now().plusHours(1).plusMinutes(5);

        List<TarefaDTO> listaTarefas =  tarefaService.buscaTarefasAgendadasPorPeriodo(horaFutura, horaFuturaMaisCinco, token);
        log.info("Quantidade de tarefas encontradas: " + listaTarefas.size());
        log.info("Tarefas encontradas: " + listaTarefas);

        listaTarefas.forEach(tarefa -> {
            notificacaoService.enviarEmail(getEmailRequest(tarefa));
            log.info("Email enviado para o usuário: " + tarefa.getEmailUsuario() + "");
            tarefaService.alterarStatus(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(), token);
            log.info("Status da tarefa alterado para: " + StatusNotificacaoEnum.NOTIFICADO);
        });

        log.info("Finalizada a busca e notificação das tarefas");

    }

    public String login(LoginRequestDTO loginRequestDTO) {
        return usuarioService.loginUsuario(loginRequestDTO).getToken();
    }

    public LoginRequestDTO getAdminLogin(){
        return LoginRequestDTO.builder()
                .email(adminEmail)
                .senha(adminPassword)
                .build();
    }

    public EmailRequestoDTO getEmailRequest(TarefaDTO tarefaDTO){
        return EmailRequestoDTO.builder()
                .nomeTarefa(tarefaDTO.getNomeTarefa())
                .descricao(tarefaDTO.getDescricao())
                .dataAgendamento(tarefaDTO.getDataAgendamento())
                .emailUsuario(tarefaDTO.getEmailUsuario())
                .build();
    }

}
