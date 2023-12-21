package fr.univrouen.delivuniv.exception;

import jakarta.validation.ConstraintViolation;
import lombok.Getter;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class ValidationException extends RuntimeException {

    private final Set<String> violations;

    public <T> ValidationException(Set<ConstraintViolation<T>> violations) {
        super("One or more errors were caught during validation.");
        this.violations =
                Objects.requireNonNull(violations).stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.toSet());
    }
}
