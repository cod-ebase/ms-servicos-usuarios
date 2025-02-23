package br.com.codebase.ms_servicos_usuarios.servicos_usuarios.Exception;

import br.com.codebase.ms_servicos_usuarios.servicos_usuarios.api.controller.UsuarioController;
import org.modelmapper.internal.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice(basePackageClasses = UsuarioController.class)
public class GlobalExceptionHandler {
    /**
     * Manipula exceções de validação e retorna um mapa com os campos inválidos e as suas mensagens de erro.
     *
     * @param ex Exceção lançada quando acontece um erro de validação nos argumentos da requisição.
     * @return ResponseEntity contendo os campos inválidos como chave e as mensagens de erro como valor, com status
     * 400 Bad request.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<?, ?>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        var map = ex.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(e -> Pair.of(e.getField(), e.getDefaultMessage()))
                    .collect(Collectors.toMap(Pair::getLeft, Pair::getRight));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<?, ?>> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("email", ex.getMessage()));
    }
}