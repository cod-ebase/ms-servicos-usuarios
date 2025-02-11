package br.com.codebase.ms_servicos_usuarios.servicos_usuarios.Exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleSQLExceptions(SQLException ex) {
        String mensagem = ex.getCause() != null ? ex.getCause().getLocalizedMessage() : ex.getMessage();
        var i = mensagem.indexOf("Detalhe:");
        if (i > 0) {
            mensagem = mensagem.substring(i);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(mensagem);
    }
}