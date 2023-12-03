package cl.bci.test.exception;

import cl.bci.test.dto.ProblemResponseDTO;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler{

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAnyException(Exception ex) {
        log.error(ex.getMessage());
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage());

        return new ResponseEntity<>(ProblemResponseDTO.builder()
                .mensaje(ex.getBindingResult().getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.joining(", ")))
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ProblemResponseDTO.builder()
                .mensaje(ex.getMessage())
                .build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CompletionException.class)
    public ResponseEntity<Object> handleProblemException(CompletionException ex) {

        if (ex.getCause() instanceof ProblemException) {
            ProblemException problemException = (ProblemException) ex.getCause();
            // rescatar solo el error
            log.error(problemException.getMessage());
            return new ResponseEntity<>(ProblemResponseDTO.builder()
                    .mensaje("El correo ya se ecuentra registrado")
                    .build(), HttpStatus.BAD_REQUEST);
        }

        if (ex.getCause() instanceof ExpiredJwtException) {
            ExpiredJwtException expiredJwtException = (ExpiredJwtException) ex.getCause();
            log.error(expiredJwtException.getMessage());
            return new ResponseEntity<>(ProblemResponseDTO.builder()
                    .mensaje(expiredJwtException.getMessage())
                    .build(), HttpStatus.UNAUTHORIZED);
        }

        log.error(ex.getMessage());
        return new ResponseEntity<>(ProblemResponseDTO.builder()
                .mensaje(ex.getMessage())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
