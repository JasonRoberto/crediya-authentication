package co.com.crediya.usecase.user;

import co.com.crediya.model.user.User;
import co.com.crediya.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserUseCase {
    private final UserRepository userRepository;
    public Mono<User> registerUser(User userToRegister) {
        return userRepository.findByEmail(userToRegister.getEmail())
                .hasElement()
                .flatMap(emailExists -> {
                    if (emailExists) {
                        return Mono.error(new IllegalArgumentException("El email ya existe"));
                    }
                    return userRepository.save(userToRegister);
                });
    }
}
