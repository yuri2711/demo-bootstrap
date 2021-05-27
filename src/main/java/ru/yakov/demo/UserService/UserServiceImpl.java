package ru.yakov.demo.UserService;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yakov.demo.dto.UserDto;
import ru.yakov.demo.model.User;
import ru.yakov.demo.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service("userService")
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }


}
