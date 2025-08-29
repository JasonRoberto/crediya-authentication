package co.com.crediya.api.mapper;

import co.com.crediya.api.dto.UserRequestDTO;
import co.com.crediya.api.dto.UserResponseDTO;
import co.com.crediya.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDTO toUserResponseDTO(User user);

    @Mapping(target = "idUser", ignore = true)
    User toUser(UserRequestDTO userRequestDTO);

    List<UserResponseDTO> toUserResponseDTOList(List<User> userList);

    List<User> toUserList(List<UserRequestDTO> userRequestDTOList);
}
