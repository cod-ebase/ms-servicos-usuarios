package br.com.codebase.ms_servicos_usuarios.servicos_usuarios.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
