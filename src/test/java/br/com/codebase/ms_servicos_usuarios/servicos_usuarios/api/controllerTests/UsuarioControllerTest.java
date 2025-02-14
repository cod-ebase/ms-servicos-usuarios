package br.com.codebase.ms_servicos_usuarios.servicos_usuarios.api.controllerTests;

import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.DTO.UsuarioDTO;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.DTO.UsuarioResponseDTO;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.Repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@SpringBootTest
public class UsuarioControllerTest {
    private final TestRestTemplate restTemplate = new TestRestTemplate(
            TestRestTemplate.HttpClientOption.ENABLE_COOKIES);
    private final String ENDPOINT_URL = "/api/users/create";
    private final String BASE_URL = "http://localhost:8095";
    private UsuarioRepository usuarioRepository;
    @BeforeEach
    void LimpaTodosUsuarios() {
        // Limpa o banco após cada teste para evitar interferência
        usuarioRepository.deleteAll();
    }
    @Test
    public void createUsuario_Sucesso() {
        var userDTO = gerarUsuarioDTO();
        HttpEntity<UsuarioDTO> request = new HttpEntity<>(userDTO);
        var response = restTemplate.postForEntity(BASE_URL + ENDPOINT_URL, request, UsuarioResponseDTO.class);
        assertNotNull(response.getBody());
        var b = response.getBody();
        Assertions.assertAll(() -> assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                             () -> assertEquals(b.getEmail(), userDTO.getEmail()),
                             () -> assertEquals(b.getNome(), userDTO.getNome()),
                             () -> assertEquals(b.getPerfil(), userDTO.getPerfil()),
                             () -> assertEquals("Usuário criado com sucesso!", b.getMessage()));
    }
    @Test
    public void createUsuario_Senha_Email_Falha() {
        var userDTO = gerarUsuarioDTO();
        // inválido, senha com tamanho menor que 6
        userDTO.setSenha("pass");
        // inválido email
        userDTO.setEmail("email_email.com");
        var request = new HttpEntity<>(userDTO);
        var response = restTemplate.postForEntity(BASE_URL + ENDPOINT_URL, request, Map.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), "O status http deve ser BAD_REQUEST");
        var body = response.getBody();
        assertNotNull(body, "O corpo da resposta não deve ser nulo");
        Assertions.assertAll("Erros de validação esperados",
                             () -> assertNotNull(body.get("senha"), "Deve existir erro para 'senha'"),
                             () -> assertNotNull(body.get("email"), "Deve existir erro para 'email'"));
        log.info("Erro de senha: {}", body.remove("senha"));
        log.info("Erro de email: {}", body.remove("email"));
        Assertions.assertTrue(body.isEmpty());
    }
    @Test
    public void checkDuplicidadeUsuario_CreateFail() {
        var userDTO = gerarUsuarioDTO();
        HttpEntity<UsuarioDTO> request = new HttpEntity<>(userDTO);
        var responseForFirstUsers = restTemplate.postForEntity(BASE_URL + ENDPOINT_URL, request,
                                                               UsuarioResponseDTO.class);
        assertEquals(HttpStatus.CREATED, responseForFirstUsers.getStatusCode());
        assertNotNull(responseForFirstUsers.getBody());
        assertEquals("Usuário criado com sucesso!", responseForFirstUsers.getBody().getMessage());
        var responseForDupEmail = restTemplate.postForEntity(BASE_URL + ENDPOINT_URL, request, Map.class);
        assertEquals(HttpStatus.CONFLICT, responseForDupEmail.getStatusCode());
        assertNotNull(responseForDupEmail.getBody());
        log.info("Erro: {}", responseForDupEmail.getBody().remove("email"));
        Assertions.assertTrue(responseForDupEmail.getBody().isEmpty());
    }
    private UsuarioDTO gerarUsuarioDTO() {
        // retorna um exemplo de registro de usuário que tende à todos os requisistos de validação
        return UsuarioDTO.builder()
                         .email("email@email.com")
                         .nome("Usuário_0")
                         .senha("Senha123456")
                         .nascimento("2000-01-28")
                         .sexo("F")
                         .perfil("CLIENT")
                         .foto("path/to/png.png")
                         .build();
    }
}
