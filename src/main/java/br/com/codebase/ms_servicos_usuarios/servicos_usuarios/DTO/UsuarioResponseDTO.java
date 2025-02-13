package br.com.codebase.ms_servicos_usuarios.servicos_usuarios.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioResponseDTO {
    private String nome;
    private String email;
    private String perfil;
    private String message;
}
