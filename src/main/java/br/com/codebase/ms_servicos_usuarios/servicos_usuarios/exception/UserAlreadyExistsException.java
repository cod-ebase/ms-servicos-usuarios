package br.com.codebase.ms_servicos_usuarios.servicos_usuarios.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
