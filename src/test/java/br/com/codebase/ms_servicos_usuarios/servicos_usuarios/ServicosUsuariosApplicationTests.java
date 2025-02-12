package br.com.codebase.ms_servicos_usuarios.servicos_usuarios;

import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.DTO.UsuarioDTO;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.Mappers.UserMapper;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.models.Sexo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
                            .nascimento("1997-12-03")
                            .build();
        var u = UserMapper.toUsuario(dto);
        System.out.println(u);
    }
}
