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
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @Email(message = "Email inválido")
    private String email;
    @EnumValidator(enumClass = Sexo.class, message = "O campo Sexo é Obrigatório")
    private String sexo;
    @NotBlank(message = "Uma foto é obrigatória")
    private String foto;
    @Size(min = 6, message = "Senha deve ter pelo menos 6 caracteres")
    private String senha;
    @EnumValidator(enumClass = PerfilUsuario.class)
    private String perfil;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String nascimento;
}
