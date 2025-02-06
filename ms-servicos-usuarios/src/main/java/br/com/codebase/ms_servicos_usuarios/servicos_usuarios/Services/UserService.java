package br.com.codebase.ms_servicos_usuarios.servicos_usuarios.Services;

import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.DTO.UsuarioDTO;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.Mappers.UserMapper;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.Repository.UsuarioRepository;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.models.PerfilUsuario;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.models.Usuario;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;
    @PreAuthorize("hasRole('ADMIN')")
    public Usuario createAdmin(UsuarioDTO usuarioDTO) {
        return createUser(usuarioDTO, PerfilUsuario.ADMIN);
    }
    public Usuario createParceiro(UsuarioDTO usuarioDTO) {
        return createUser(usuarioDTO, PerfilUsuario.PARCEIRO);
    }
    public Usuario createCliente(UsuarioDTO usuarioDTO) {
        return createUser(usuarioDTO, PerfilUsuario.CLIENT);
    }
    private Usuario createUser(UsuarioDTO usuarioDTO, PerfilUsuario perfil) {
        var user = UserMapper.toUsuario(usuarioDTO);
        user.setPerfil(perfil);
        return usuarioRepository.save(user);
    }
}
