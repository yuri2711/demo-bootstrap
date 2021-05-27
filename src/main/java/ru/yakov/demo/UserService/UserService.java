package ru.yakov.demo.UserService;


import ru.yakov.demo.dto.UserDto;
import ru.yakov.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    User saveUser(User user);
    User getUser(int id);
    void updateUser(User user);
    void deleteUser(int id);
    List<User> getAllUsers();
    User findByLogin(String login);
}
