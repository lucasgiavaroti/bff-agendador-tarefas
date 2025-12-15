package com.lucasgiavaroti.bff_agendador_tarefas.business;

import com.lucasgiavaroti.bff_agendador_tarefas.business.dto.in.EnderecoRequestDTO;
import com.lucasgiavaroti.bff_agendador_tarefas.business.dto.in.LoginRequestDTO;
import com.lucasgiavaroti.bff_agendador_tarefas.business.dto.in.TelefoneRequestDTO;
import com.lucasgiavaroti.bff_agendador_tarefas.business.dto.in.UsuarioRequestDTO;
import com.lucasgiavaroti.bff_agendador_tarefas.business.dto.out.*;
import com.lucasgiavaroti.bff_agendador_tarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient usuarioClient;

    public UsuarioDTO salvaUsuario(UsuarioRequestDTO dto){
        return usuarioClient.salvarUsuario(dto);
    }

    public UsuarioDTO buscaUsuarioPorEmail(String email, String token){
       return usuarioClient.buscaUsuarioPorEmail(email, token);
    }

    public void deletarUsuarioPorEmail(String email, String token){
        usuarioClient.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioDTO atualizaDadosUsuario(UsuarioRequestDTO dto, String token){
        return usuarioClient.atualizaDadosUsuario(dto, token);
    }

    public EnderecoDTO atualizaEndereco(Long id, EnderecoRequestDTO dto, String token){
        return usuarioClient.atualizaEnderecoUsuario(dto, id, token);
    }

    public TelefoneDTO atualizaTelefone(Long id, TelefoneRequestDTO dto, String token){
        return usuarioClient.atualizaTelefoneUsuario(dto, id, token);
    }

    public EnderecoDTO cadastraEndereco(String token, EnderecoRequestDTO dto){
        return usuarioClient.cadastraEndereco(dto, token);
    }

    public TelefoneDTO cadastraTelefone(String token, TelefoneRequestDTO dto){
        return usuarioClient.cadastraTelefone(dto, token);
    }

    public ViaCepDTO buscaEnderecoPorCep(String cep){
        return usuarioClient.buscaEnderecoPorCep(cep);
    }

    public LoginDTO loginUsuario(LoginRequestDTO usuarioDTO){
        return usuarioClient.login(usuarioDTO);
    }

}
