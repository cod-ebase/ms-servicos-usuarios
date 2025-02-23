package br.com.codebase.ms_servicos_usuarios.servicos_usuarios.Exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}