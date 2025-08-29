package co.com.crediya.model.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthenticationErrorEnum {

    NULL_OR_EMPTY("AUTH-001", "El campo <f0> no puede estar vacío",HttpStatus.CONFLICT),
    EMAIL_ALREADY_REGISTERED("AUTH-002", "El correo electrónico ya está registrado.", HttpStatus.CONFLICT); // 409


    private final String code;
    private final String message;
    private final HttpStatus httpStatus;


}
