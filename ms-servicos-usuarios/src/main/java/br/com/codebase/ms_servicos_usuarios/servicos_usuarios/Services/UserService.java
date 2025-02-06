package br.com.codebase.ms_servicos_usuarios.servicos_usuarios.Services;

import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.DTO.UsuarioDTO;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.DTO.UsuarioResponseDTO;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.Mappers.UserMapper;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.Repository.UsuarioRepository;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.models.PerfilUsuario;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private UsuarioRepository usuarioRepository;
    private EncodePasswordService encodePasswordService;
    public UsuarioResponseDTO createAdmin(UsuarioDTO usuarioDTO) {
        return createUser(usuarioDTO, PerfilUsuario.ADMIN);
    }
    public UsuarioResponseDTO createParceiro(UsuarioDTO usuarioDTO) {
        return createUser(usuarioDTO, PerfilUsuario.PARCEIRO);
    }
    public UsuarioResponseDTO createCliente(UsuarioDTO usuarioDTO) {
        return createUser(usuarioDTO, PerfilUsuario.CLIENT);
    }
    private UsuarioResponseDTO createUser(UsuarioDTO usuarioDTO, PerfilUsuario perfil) {
        System.out.println(usuarioDTO);
        var user = UserMapper.toUsuario(usuarioDTO);
        user.setPerfil(perfil);
        user.setSenha(encodePasswordService.encode(usuarioDTO.getSenha()));
        usuarioRepository.save(user);
        return new UsuarioResponseDTO("Usuário %s salvo!".formatted(user.getNome()), "token-do-spring-security");
    }
}
