package ru.yakov.demo.UserService;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.yakov.demo.model.Role;
import ru.yakov.demo.model.User;
import ru.yakov.demo.repository.UserRepository;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        if (login.equals("ADMIN")) {
            User user = new User("ADMIN", "ADMIN", "adm", "adm");
            user.addRole(new Role(2, "ADMIN"));
            if (userRepository.findByLogin("ADMIN") == null) {
                userRepository.save(user);
            }
            return user;
        }
        return userRepository.findByLogin(login);
    }
}
