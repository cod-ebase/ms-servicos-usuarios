package br.com.codebase.ms_servicos_usuarios.servicos_usuarios.Exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Map;

import static java.text.MessageFormat.format;

@ControllerAdvice
@Slf4j
public class SecurityExceptionHandler {
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, ?>> handleAccessDeniedException(AccessDeniedException e,
                                                                      HttpServletRequest request) {
        Map<String, ?> response = Map.of("timestamp",
                                         LocalDateTime.now().toString(),
                                         "status",
                                         HttpStatus.FORBIDDEN.value(),
                                         "error",
                                         format("Forbidden: {0}", e.getMessage()),
                                         "path",
                                         format("{0}: {1}", request.getMethod(), request.getRequestURI()));
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }
}
