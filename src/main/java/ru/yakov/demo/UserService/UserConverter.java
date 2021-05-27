package ru.yakov.demo.UserService;

import org.springframework.stereotype.Component;
import ru.yakov.demo.dto.UserDto;
import ru.yakov.demo.model.User;

@Component
public class UserConverter {

    public User fromUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getLogin());
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        return user;
    }

    public UserDto fromUserToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .login(user.getEmail())
                .name(user.getName())
                .lastName(user.getLastName())
                .build();
    }
}
