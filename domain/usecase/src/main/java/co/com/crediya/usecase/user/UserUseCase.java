package co.com.crediya.usecase.user;

import co.com.crediya.model.exception.AuthenticationErrorEnum;
import co.com.crediya.model.exception.AuthenticationException;
import co.com.crediya.model.user.User;
import co.com.crediya.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
public class UserUseCase {

    private final UserRepository userRepository;

    public Mono<User> registerUser(User userToRegister) {
        return userRepository.findByEmail(userToRegister.getEmail())
                .hasElement()
                .flatMap(emailExists -> {
                    if (emailExists) {
                        return Mono.error(new AuthenticationException(AuthenticationErrorEnum.EMAIL_ALREADY_REGISTERED));
                    }
                    //log.info("Email disponible. Procediendo a registrar usuario: {}", userToRegister.getEmail());
                    return userRepository.save(userToRegister);
                });

        /*return userRepository.findByEmail(userToRegister.getEmail())
                // Si el Mono anterior emite un usuario (email encontrado), lanzamos un error.
                .flatMap(existingUser -> Mono.error(ExceptionFactory.EMAIL_ALREADY_REGISTERED.get()))
                // Si el Mono anterior estaba vacío (email no encontrado), nos cambiamos a este nuevo Mono.
                .switchIfEmpty(userRepository.save(userToRegister))
                // Forzamos el tipo de retorno a Mono<User>
                .cast(User.class);*/
    }
}


