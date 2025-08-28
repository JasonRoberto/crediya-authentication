package co.com.crediya.api;
import co.com.crediya.api.dto.UserRequestDTO;
import co.com.crediya.api.dto.UserResponseDTO;
import co.com.crediya.api.mapper.UserMapper;
import co.com.crediya.usecase.user.UserUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserController {
    private final UserUseCase userUseCase;
    private final UserMapper userMapper;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UserResponseDTO> registerUser(@RequestBody UserRequestDTO userToRegister) {
        return Mono.just(userToRegister)
                //.flatMap(ValidationUtils)
                .map(userMapper::toUser)
                .flatMap(userUseCase::registerUser)
                .map(userMapper::toUserResponseDTO);
    }


    @GetMapping(path = "/usecase/path")
    public Mono<String> commandName() {
//      return useCase.doAction();
        return Mono.just("");
    }
}
