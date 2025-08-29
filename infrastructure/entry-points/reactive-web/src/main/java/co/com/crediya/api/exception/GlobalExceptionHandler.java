package co.com.crediya.api.exception;

import co.com.crediya.api.dto.ErrorDetailDTO;
import co.com.crediya.api.dto.ErrorResponseDTO;
import co.com.crediya.model.exception.AuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * Maneja las excepciones de negocio controladas (AuthenticationException).
     * Devuelve una respuesta HTTP específica del error de negocio (ej. 409, 404).
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponseDTO> handleAuthenticationException(AuthenticationException ex) {
        var errorEnum = ex.getError();
        log.warn("Excepción de negocio controlada: {} - {}", errorEnum.getCode(), errorEnum.getMessage());

        var errorResponse = ErrorResponseDTO.builder()
                .errorCode(errorEnum.getCode())
                .message(errorEnum.getMessage())
                .httpStatus(errorEnum.getHttpStatus().value())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(errorResponse, errorEnum.getHttpStatus());
    }

    /**
     * Maneja los errores de validación de DTOs (anotación @Valid).
     * Siempre devuelve un HTTP 400 Bad Request con una lista detallada de los campos que fallaron.
     */
    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationException(WebExchangeBindException ex) {
        List<ErrorDetailDTO> errorDetails = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new ErrorDetailDTO(
                        fieldError.getField(),
                        fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        log.warn("Error de validación en la petición: {}", errorDetails);

        var errorResponse = ErrorResponseDTO.builder()
                .errorCode("VALIDATION_ERROR")
                .message("La petición contiene datos inválidos. Por favor, revise los detalles.")
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .errors(errorDetails)
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Manejador "catch-all" para cualquier otra excepción no controlada.
     * Siempre devuelve un HTTP 500 para no exponer detalles internos del sistema.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex) {
        log.error("Excepción no controlada:", ex); // Loguea el stack trace completo

        var errorResponse = ErrorResponseDTO.builder()
                .errorCode("INTERNAL_SERVER_ERROR")
                .message("Ha ocurrido un error inesperado en el servidor.")
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
