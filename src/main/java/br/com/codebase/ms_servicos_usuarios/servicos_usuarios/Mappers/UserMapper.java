package br.com.codebase.ms_servicos_usuarios.servicos_usuarios.Mappers;

import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.DTO.UsuarioDTO;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.DTO.UsuarioResponseDTO;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.models.Usuario;
import org.modelmapper.ModelMapper;

import java.time.Instant;

public final class UserMapper {
    public static Usuario toUsuario(UsuarioDTO usuarioDTO) {
        var user = new ModelMapper().typeMap(UsuarioDTO.class, Usuario.class).addMappings(m -> {
            m.map(UsuarioDTO::getNome, Usuario::setNome);
            m.map(UsuarioDTO::getEmail, Usuario::setEmail);
            m.map(UsuarioDTO::getSexo, Usuario::setSexo);
            m.map(UsuarioDTO::getFoto, Usuario::setFoto);
            m.map(UsuarioDTO::getNascimento, Usuario::setDataDeNascimento);
            m.map(UsuarioDTO::getPerfil, Usuario::setPerfil);
        }).map(usuarioDTO);
        var now = Instant.now();
        user.setDataDeCriacao(now);
        user.setDataUltimaModificacao(now);
        return user;
    }
    public static UsuarioResponseDTO toUsuarioResponseDTO(UsuarioDTO usuario) {
        return new ModelMapper().typeMap(UsuarioDTO.class, UsuarioResponseDTO.class).addMappings(m -> {
            m.map(UsuarioDTO::getNome,UsuarioResponseDTO::setNome);
            m.map(UsuarioDTO::getEmail,UsuarioResponseDTO::setEmail);
            m.map(UsuarioDTO::getPerfil,UsuarioResponseDTO::setPerfil);
        }).map(usuario);
    }
}
