package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    void saveUser(User user);

    List<User> getAllUsers();

    void deleteUserById(int id);

    void updateUser(User user, int id);

    User getUserById(int id);

    User getByUsername(String username);
}

