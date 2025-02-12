package br.com.codebase.ms_servicos_usuarios.servicos_usuarios.Services;

import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.DTO.UsuarioDTO;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.DTO.UsuarioResponseDTO;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.Mappers.UserMapper;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.Repository.UsuarioRepository;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.models.Usuario;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private UsuarioRepository usuarioRepository;
    private EncodePasswordService encodePasswordService;
    @PreAuthorize("hasRole('ADMIN')")
    private UsuarioResponseDTO createAdmin(UsuarioDTO usuarioDTO) {
        return createUserAux(usuarioDTO);
    }
    @Transactional
    @PreAuthorize("permitAll()")
    public UsuarioResponseDTO createUser(UsuarioDTO usuarioDTO) {
        return usuarioDTO.getPerfil().equalsIgnoreCase("ADMIN") ? createAdmin(usuarioDTO) : createUser(usuarioDTO);
    }
    private UsuarioResponseDTO createUserAux(UsuarioDTO usuarioDTO) {
        if (usuarioRepository.existsByEmail(usuarioDTO.getEmail()))
            return UsuarioResponseDTO.builder().message("Email " + usuarioDTO.getEmail() + " já cadastrado!").build();
        var user = convertUsuarioDTOToUsuario(usuarioDTO);
        usuarioRepository.save(user);
        return UserMapper.toUsuarioResponseDTO(usuarioDTO);
    }
    private Usuario convertUsuarioDTOToUsuario(UsuarioDTO usuarioDTO) {
        var user = UserMapper.toUsuario(usuarioDTO);
        user.setSenha(encodePasswordService.encode(usuarioDTO.getSenha()));
        return user;
    }
}
