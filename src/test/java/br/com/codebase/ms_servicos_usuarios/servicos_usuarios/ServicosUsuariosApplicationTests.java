package br.com.codebase.ms_servicos_usuarios.servicos_usuarios;

import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.DTO.UsuarioDTO;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.Mappers.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ServicosUsuariosApplicationTests {
    @Test
    void contextLoads() {
    }
    @Test
    void testeUsuarioDTO_Para_Usuario_Sucesso() {
        var dto = UsuarioDTO.builder()
                .email("teste@gmail.com")
                .foto("Teste/out.png")
                .nascimento("1997-12-03")
                .nome("Meu nome")
                .perfil("ADMIN")
                .senha("Testesenha")
                .sexo("M")
                .build();
        var usuario = UserMapper.toUsuario(dto);
        assertNotNull(usuario, "O objeto de mapeamento não deve ser nulo");
        assertAll("Verifica o mapeamento de atributos",
                  () -> assertEquals(dto.getEmail(), usuario.getEmail()),
                  () -> assertEquals(dto.getEmail(), usuario.getEmail(), "Email incorreto!"),
                  () -> assertEquals(dto.getFoto(), usuario.getFoto(), "Foto incorreto!"),
                  () -> assertEquals(dto.getSexo(), usuario.getSexo().toString(), "Sexo incorreto!"),
                  () -> assertEquals(dto.getNascimento(),
                                     usuario.getDataDeNascimento().toString(),
                                     "Nascimento incorreto!"),
                  () -> assertEquals(dto.getSenha(), usuario.getSenha(), "Senha incorreto!"),
                  () -> assertEquals(dto.getPerfil(), usuario.getPerfil().toString(), "Perfil incorreto!"),
                  () -> assertEquals(dto.getNome(), usuario.getNome(), "Nome incorreto!"));
    }
    @Test
    void testeUsuarioDTO_Para_UsuarioResponseDTO_Sucesso() {
        var userdto = UsuarioDTO.builder()
                .nome("Usuario_0")
                .email("email@email.com")
                .senha("senha123456")
                .perfil("PARCEIRO")
                .sexo("F")
                .foto("path/to/png.png")
                .nascimento("1997-12-03")
                .build();
        var userResponseDTO = UserMapper.toUsuarioResponseDTO(userdto);
        log.info("UsuarioResponseDTO: {}", userResponseDTO);
        assertNotNull(userResponseDTO);
        assertAll("Verifica o mapeamento de atributos",
                  () -> assertEquals(userdto.getNome(), userResponseDTO.getNome(), "Nome incorreto!"),
                  () -> assertEquals(userdto.getEmail(), userResponseDTO.getEmail(), "Email incorreto!"),
                  () -> assertEquals(userdto.getPerfil(), userResponseDTO.getPerfil(), "Perfil incorreto!"),
                  () -> assertNull(userResponseDTO.getMessage())); // A mensagem nesse momento está não atribuída
    }
}
