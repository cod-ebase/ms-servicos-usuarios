package br.com.codebase.ms_servicos_usuarios.servicos_usuarios.dto;

// import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserDto implements Serializable {
    private Long id;
    private String name;
    private String email;
    private boolean status;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdated;
    private LocalDateTime lastLogin;
}
