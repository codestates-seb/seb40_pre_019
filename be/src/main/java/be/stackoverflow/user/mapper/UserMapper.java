package be.stackoverflow.user.mapper;

import be.stackoverflow.user.dto.UserDto;
import be.stackoverflow.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userPostToUser(UserDto.Post requestBody);

    User userPatchToUser(UserDto.Patch requestBody);

    UserDto.Response userToUserResponse(User user);

    List<UserDto.Response> usersToUserReponses(List<User> users);
}
