package com.lucasgiavaroti.bff_agendador_tarefas.controller;

import com.lucasgiavaroti.bff_agendador_tarefas.business.UsuarioService;
import com.lucasgiavaroti.bff_agendador_tarefas.business.dto.in.EnderecoRequestDTO;
import com.lucasgiavaroti.bff_agendador_tarefas.business.dto.in.LoginRequestDTO;
import com.lucasgiavaroti.bff_agendador_tarefas.business.dto.in.TelefoneRequestDTO;
import com.lucasgiavaroti.bff_agendador_tarefas.business.dto.in.UsuarioRequestDTO;
import com.lucasgiavaroti.bff_agendador_tarefas.business.dto.out.EnderecoDTO;
import com.lucasgiavaroti.bff_agendador_tarefas.business.dto.out.LoginDTO;
import com.lucasgiavaroti.bff_agendador_tarefas.business.dto.out.TelefoneDTO;
import com.lucasgiavaroti.bff_agendador_tarefas.business.dto.out.UsuarioDTO;
import com.lucasgiavaroti.bff_agendador_tarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Cadastro e Login de Usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Salvar Usuário",description = "Cria um novo usuário no sistema")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "409", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro interno de servidor")
    public ResponseEntity<UsuarioDTO> salvarUsuario(@RequestBody UsuarioRequestDTO dto){
        return ResponseEntity.ok(usuarioService.salvaUsuario(dto));
    }

    @PostMapping("/login")
    @Operation(summary = "Login de Usuário",description = "Faz o login do usuário no sistema")
    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro interno de servidor")
    public ResponseEntity<LoginDTO> login (@RequestBody LoginRequestDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.loginUsuario(usuarioDTO));
    }

    @GetMapping
    @Operation(summary = "Buscar Usuário",description = "Busca usuário por e-mail no sistema")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro interno de servidor")
    public ResponseEntity<UsuarioDTO> buscaUsuarioPorEmail(@RequestParam("email") String email, @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.buscaUsuarioPorEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deletar Usuário",description = "Deleta um usuário por e-mail no sistema")
    @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro interno de servidor")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable("email") String email, @RequestHeader(name = "Authorization", required = false) String token) {
        usuarioService.deletarUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualizar Dados do Usuário",description = "Atualizar dados do usuário no sistema")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro interno de servidor")
    public ResponseEntity<UsuarioDTO> atualizaDadosUsuario(@RequestBody UsuarioRequestDTO dto, @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(dto, token));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualizar Dados de Endereço do Usuário",description = "Atualizar dados do endereço no sistema")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro interno de servidor")
    public ResponseEntity<EnderecoDTO> atualizaEnderecoUsuario(@RequestBody EnderecoRequestDTO dto, @RequestParam Long id, @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, dto, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualizar Dados de Telefone do Usuário",description = "Atualizar dados do telefone no sistema")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro interno de servidor")
    public ResponseEntity<TelefoneDTO> atualizaTelefoneUsuario(@RequestBody TelefoneRequestDTO dto, @RequestParam Long id, @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, dto, token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Salvar Endereço",description = "Criar novo endereço no sistema")
    @ApiResponse(responseCode = "200", description = "Endereço criado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro interno de servidor")
    public ResponseEntity<EnderecoDTO> cadastraEndereco(@RequestBody EnderecoRequestDTO dto, @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, dto));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Salvar Telefone",description = "Criar novo telefone no sistema")
    @ApiResponse(responseCode = "200", description = "Telefone criado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro interno de servidor")
    public ResponseEntity<TelefoneDTO> cadastraTelefone(@RequestBody TelefoneRequestDTO dto, @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token, dto));
    }

}
