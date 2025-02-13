package br.com.codebase.ms_servicos_usuarios.servicos_usuarios.Exception;

import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.DTO.UsuarioResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                                .collect(Collectors.joining(", ", "{", "}"));
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<UsuarioResponseDTO> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        var b = UsuarioResponseDTO.builder().message(ex.getMessage()).build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(b);
    }
}