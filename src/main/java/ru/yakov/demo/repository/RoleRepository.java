package ru.yakov.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yakov.demo.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
//    Role findRoleByName(String name);
}
