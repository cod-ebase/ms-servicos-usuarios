package br.com.codebase.ms_servicos_usuarios.servicos_usuarios.DTO;

import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.models.PerfilUsuario;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.models.Sexo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Data
public class UsuarioDTO {
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @Email(message = "Email inválido")
    private String email;
    @NotNull(message = "O campo Sexo é Obrigatório")
    private Sexo sexo;
    @NotBlank(message = "Uma foto é obrigatória")
    private String foto;
    @Size(min = 6, message = "Senha deve ter pelo menos 6 caracteres")
    private String senha;
    //Adicionar validação de data de nascimento no futuro
    @NotNull
    private LocalDate nascimento;
}
