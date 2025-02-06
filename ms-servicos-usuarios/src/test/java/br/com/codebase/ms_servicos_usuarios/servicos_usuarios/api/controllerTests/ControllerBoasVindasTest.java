package br.com.codebase.ms_servicos_usuarios.servicos_usuarios.api.controllerTests;

import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.api.controller.ControllerBoasVindas;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ControllerBoasVindas.class)
public class ControllerBoasVindasTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testBoaV() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().string("Bem Vindo á nossa api"));
    }
}