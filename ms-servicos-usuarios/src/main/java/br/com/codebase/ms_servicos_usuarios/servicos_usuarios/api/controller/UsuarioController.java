package br.com.codebase.ms_servicos_usuarios.servicos_usuarios.Controller;

import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.DTO.UsuarioDTO;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.Services.UserService;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.models.Usuario;
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
    // Endpoint privado (apenas ADMIN)
    @PostMapping("/admin")
    public ResponseEntity<Usuario> createAdmin(@Valid @RequestBody UsuarioDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createAdmin(request));
    }
    // Endpoint público
    @PostMapping("/parceiro")
    public ResponseEntity<Usuario> createParceiro(@Valid @RequestBody UsuarioDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createParceiro(request));
    }
    // Endpoint público
    @PostMapping("/cliente")
    public ResponseEntity<Usuario> createCliente(@Valid @RequestBody UsuarioDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createCliente(request));
    }
}
