package br.com.codebase.ms_servicos_usuarios.servicos_usuarios.Services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EncodePasswordService {
    private PasswordEncoder passwordEncoder;
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }
}
