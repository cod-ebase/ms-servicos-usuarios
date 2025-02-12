package br.com.codebase.ms_servicos_usuarios.servicos_usuarios.api.controller;

import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.DTO.UsuarioDTO;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.DTO.UsuarioResponseDTO;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.Services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class UsuarioController {
    private UserService userService;
    @PostMapping("/create")
    public ResponseEntity<UsuarioResponseDTO> create(@Valid @RequestBody UsuarioDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request));
    }
}
