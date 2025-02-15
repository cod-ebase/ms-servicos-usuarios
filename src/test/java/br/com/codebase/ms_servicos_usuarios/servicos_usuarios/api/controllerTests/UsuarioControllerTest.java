package br.com.codebase.ms_servicos_usuarios.servicos_usuarios.api.controllerTests;

import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.DTO.UsuarioDTO;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.Repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@SpringBootTest
@AllArgsConstructor(onConstructor = @__(@Autowired))
@AutoConfigureMockMvc
public class UsuarioControllerTest {
    private static final String BASE_URL = "http://localhost:8095";
    private static final String ENDPOINT_URL = "/api/users/create";
    @Autowired
    private MockMvc mock;
    private ObjectMapper objectMapper;
    private UsuarioRepository usuarioRepository;
    @BeforeEach
    void LimpaTodosUsuarios() {
        // Limpa o banco após cada teste para evitar interferência
        usuarioRepository.deleteAll();
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    public void createUsuario_ADMIN_Sucesso() throws Exception {
        var userDTO = gerarUsuarioDTO();
        userDTO.setPerfil("ADMIN");
        var result = performMockUserRegistrationAndLog(userDTO);
        expectSuccessUserRegistration(result, userDTO);
    }
    @Test
    public void createUsuario_ADMIN_Falha() throws Exception {
        var userDTO = gerarUsuarioDTO();
        userDTO.setPerfil("ADMIN");
        var result = performMockUserRegistrationAndLog(userDTO);
        result.andExpect(status().isForbidden());
    }
    /**
     * Método responsável pelo registro de usuário, exceto caso o usuário tenha ROLE ADMIN.
     *
     * @throws Exception lançada caso ocorra algum erro no teste.
     */
    @Test
    public void createUsuario_Sucesso() throws Exception {
        var userDTO = gerarUsuarioDTO();
        var result = performMockUserRegistrationAndLog(userDTO);
        expectSuccessUserRegistration(result, userDTO);
    }
    @Test
    public void createUsuario_Senha_Email_Falha() throws Exception {
        var userDTO = gerarUsuarioDTO();
        userDTO.setSenha("pass");            // inválido, senha com tamanho menor que 6
        userDTO.setEmail("email_email.com"); // e-mail inválido
        var result = performMockUserRegistrationAndLog(userDTO);
        // verifica a ocorrência de erros de validação nos campos senha e e-mail.
        result.andExpectAll(content().contentType(MediaType.APPLICATION_JSON),
                            status().isBadRequest(),
                            jsonPath("$.senha").exists(),
                            jsonPath("$.email").exists(),
                            jsonPath("$.size()").value(2));
    }
    /*
     * Método verifica a ocorrência de falha ao tentar registrar mais de um usuário com mesmo e-mail.
     */
    @Test
    public void emailJaExiste_Falha() throws Exception {
        var userDTO = gerarUsuarioDTO();
        ///  usuário é registrado pela primeira vez com sucesso...
        var result1 = performMockUserRegistrationAndLog(userDTO);
        expectSuccessUserRegistration(result1, userDTO);
        var result = performMockUserRegistrationAndLog(userDTO);
        /// Verifica a ocorrência de conflito ao tentar registrar dois usuários com mesmo e-mail
        result.andExpectAll(content().contentType(MediaType.APPLICATION_JSON),
                            status().isConflict(),
                            jsonPath("$.email").exists(),
                            jsonPath("$.size()").value(1));
    }
    private UsuarioDTO gerarUsuarioDTO() {
        // retorna um exemplo de registro de usuário com todos os campos válidos
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
    /**
     * @param objRequest objeto que descreve parâmetros da requisição
     * @param <T>        parâmetro genérico para suportar objetos em requisições
     * @return ResultActions
     * @throws Exception caso o teste falhe.
     */
    private <T> ResultActions performMockUserRegistrationAndLog(T objRequest) throws Exception {
        return mock.perform(post(BASE_URL + ENDPOINT_URL).contentType(MediaType.APPLICATION_JSON)
                                                         .content(objectMapper.writeValueAsString(objRequest)))
                   .andDo(r -> log.info("Response {}", r.getResponse().getContentAsString()));
    }
    private void expectSuccessUserRegistration(ResultActions result, UsuarioDTO userDTO) throws Exception {
        result.andExpectAll(content().contentType(MediaType.APPLICATION_JSON),
                            status().isCreated(),
                            jsonPath("$.nome").value(userDTO.getNome()),
                            jsonPath("$.email").value(userDTO.getEmail()),
                            jsonPath("$.perfil").value(userDTO.getPerfil()),
                            jsonPath("$.message").value("Usuário criado com sucesso!"),
                            // garante que estão sendo retornados exatamente 4 mensagens definidas
                            jsonPath("$.length()").value(4));
    }
}
