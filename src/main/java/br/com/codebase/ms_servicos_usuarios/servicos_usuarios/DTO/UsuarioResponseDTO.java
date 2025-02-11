package br.com.codebase.ms_servicos_usuarios.servicos_usuarios.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioResponseDTO {
    String message;
    String token;
}
