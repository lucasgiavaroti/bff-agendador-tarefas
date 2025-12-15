package com.lucasgiavaroti.bff_agendador_tarefas.infrastructure.client;

import com.lucasgiavaroti.bff_agendador_tarefas.business.dto.in.EnderecoRequestDTO;
import com.lucasgiavaroti.bff_agendador_tarefas.business.dto.in.LoginRequestDTO;
import com.lucasgiavaroti.bff_agendador_tarefas.business.dto.in.TelefoneRequestDTO;
import com.lucasgiavaroti.bff_agendador_tarefas.business.dto.in.UsuarioRequestDTO;
import com.lucasgiavaroti.bff_agendador_tarefas.business.dto.out.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping()
    UsuarioDTO buscaUsuarioPorEmail(@RequestParam("email") String email, @RequestHeader( "Authorization") String token);

    @PostMapping
    UsuarioDTO salvarUsuario(@RequestBody UsuarioRequestDTO dto);

    @PostMapping("/login")
    LoginDTO login (@RequestBody LoginRequestDTO usuarioDTO);

    @DeleteMapping("/{email}")
    Void deletaUsuarioPorEmail(@PathVariable("email") String email, @RequestHeader( "Authorization") String token) ;

    @PutMapping
    UsuarioDTO atualizaDadosUsuario(@RequestBody UsuarioRequestDTO dto, @RequestHeader( "Authorization")String token);

    @PutMapping("/endereco")
    EnderecoDTO atualizaEnderecoUsuario(@RequestBody EnderecoRequestDTO dto, @RequestParam Long id, @RequestHeader( "Authorization") String token);

    @PutMapping("/telefone")
    TelefoneDTO atualizaTelefoneUsuario(@RequestBody TelefoneRequestDTO dto, @RequestParam Long id, @RequestHeader( "Authorization") String token);

    @PostMapping("/endereco")
    EnderecoDTO cadastraEndereco (@RequestBody EnderecoRequestDTO dto, @RequestHeader( "Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDTO cadastraTelefone(@RequestBody TelefoneRequestDTO dto, @RequestHeader( "Authorization") String token);

    @GetMapping("/endereco/{cep}")
    ViaCepDTO buscaEnderecoPorCep(@PathVariable("cep") String cep);

}
