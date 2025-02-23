package br.com.codebase.ms_servicos_usuarios.servicos_usuarios.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, String> {
    private Set<String> valoresPermitidos;
    @Override
    public void initialize(EnumValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.valoresPermitidos = Arrays.stream(constraintAnnotation.enumClass().getEnumConstants())
                                       .map(Enum::toString)
                                       .map(String::toLowerCase)
                                       .collect(Collectors.toSet());
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && valoresPermitidos.contains(value.toLowerCase());
    }
}
