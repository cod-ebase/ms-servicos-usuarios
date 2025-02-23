package br.com.codebase.ms_servicos_usuarios.servicos_usuarios.Services;

import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.DTO.UsuarioDTO;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.DTO.UsuarioResponseDTO;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.Exception.EmailAlreadyExistsException;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.Mappers.UserMapper;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.Repository.UsuarioRepository;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.models.Usuario;
import jakarta.transaction.Transactional;
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
    private AdminService adminService;
    @Transactional
    public UsuarioResponseDTO createUser(UsuarioDTO usuarioDTO) {
        if (usuarioDTO.getPerfil().equalsIgnoreCase("ADMIN")) adminService.AdminCreationValidation(usuarioDTO);
        return createUserAux(usuarioDTO);
    }
    private UsuarioResponseDTO createUserAux(UsuarioDTO usuarioDTO) {
        validateUser(usuarioDTO.getEmail());
        var user = createUserEntity(usuarioDTO);
        usuarioRepository.save(user);
        return buildresponse(usuarioDTO);
    }
    private UsuarioResponseDTO buildresponse(UsuarioDTO usuarioDTO) {
        var response = UserMapper.toUsuarioResponseDTO(usuarioDTO);
        response.setMessage("Usuário criado com sucesso!");
        return response;
    }
    private Usuario createUserEntity(UsuarioDTO usuarioDTO) {
        var user = UserMapper.toUsuario(usuarioDTO);
        user.setSenha(encodePasswordService.encode(usuarioDTO.getSenha()));
        return user;
    }
    void validateUser(String email) {
        if (usuarioRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException("Email %s já existe!".formatted(email));
        }
    }
}
