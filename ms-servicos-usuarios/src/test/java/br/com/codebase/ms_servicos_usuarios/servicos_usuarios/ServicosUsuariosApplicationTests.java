package br.com.codebase.ms_servicos_usuarios.servicos_usuarios;

import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.DTO.UsuarioDTO;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.Mappers.UserMapper;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.models.PerfilUsuario;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.models.Sexo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class ServicosUsuariosApplicationTests {
    @Test
    void contextLoads() {
    }
    @Test
    void testMapper() {
        var dto = UsuarioDTO.builder()
                            .foto("Teste/out.png")
                            .email("teste@gmail.com")
                            .sexo(Sexo.M)
                            .senha("Testesenha")
                            .nome("Meu nome")
                            .nascimento(LocalDate.of(1998, 6, 30))
                            .build();
        var u=UserMapper.toUsuario(dto);
        System.out.println(u);
    }
}
