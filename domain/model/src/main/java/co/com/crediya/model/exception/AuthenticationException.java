package co.com.crediya.model.exception;

import lombok.Getter;

@Getter
public class AuthenticationException extends RuntimeException {

    private final AuthenticationErrorEnum error;

    public AuthenticationException(AuthenticationErrorEnum error) {
        super(error.getMessage());
        this.error = error;
    }

}
