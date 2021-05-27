package ru.yakov.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.yakov.demo.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "select u from User u where u.email = ?1")
    User findByLogin(String login);
}
