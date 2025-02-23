package br.com.codebase.ms_servicos_usuarios.servicos_usuarios.services;

import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.dto.UserDto;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.dto.UserInputDto;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.entities.Role;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.entities.User;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.exception.UserAlreadyExistsException;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.exception.UserNotFoundException;
import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServices {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public UserServices(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserDto createUserRoleClient(UserInputDto userInputDto) {
        if (userRepository.existsByEmail(userInputDto.getEmail()))
            throw new UserAlreadyExistsException("User already exist");

        User user = User.builder()
                .id(userInputDto.getId())
                .name(userInputDto.getName())
                .email(userInputDto.getEmail())
                .password(new BCryptPasswordEncoder().encode(userInputDto.getPassword()))
                .role(Role.USER)
                .status(true)
                .build();

        if (userInputDto.getId() == null) user.setCreatedAt(LocalDateTime.now());
        else user.setLastUpdated(LocalDateTime.now());

        user = userRepository.save(user);

        return modelMapper.map(user, UserDto.class);
    }

    public UserDto findByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty())
            throw new UserNotFoundException("User not found");

        return modelMapper.map(userOptional.get(), UserDto.class);
    }

}
