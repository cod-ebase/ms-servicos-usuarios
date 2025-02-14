package br.com.codebase.ms_servicos_usuarios.servicos_usuarios.DTO;

import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.models.PerfilUsuario;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.models.Sexo;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.validation.EnumValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@Builder
@Data
public class UsuarioDTO {
    @NotBlank(message = "{validation.usuario.nome}")
    private String nome;
    @Email(message = "{validation.usuario.email}")
    private String email;
    @EnumValidator(enumClass = Sexo.class, message = "{validation.usuario.sexo}")
    private String sexo;
    @NotBlank(message = "{validation.usuario.foto}")
    private String foto;
    @Size(min = 6, message = "{validation.usuario.senha}")
    private String senha;
    @EnumValidator(enumClass = PerfilUsuario.class)
    private String perfil;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String nascimento;
}
