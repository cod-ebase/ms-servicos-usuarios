package br.com.codebase.ms_servicos_usuarios.servicos_usuarios.Services;

import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.DTO.UsuarioDTO;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdminService {
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    /**
     * Esse método é chamado para verificar
     *  Se existe usuário autenticado
     *  E a role desse usuário é ADMIN
     * Dessa forma, é garantido que somente admins possam registrar outros admins
     * */
    public void AdminCreationValidation(UsuarioDTO usuarioDTO) {
        log.info("Registrando usuário admin: {}, {}", usuarioDTO.getNome(), usuarioDTO.getEmail());
    }
}
