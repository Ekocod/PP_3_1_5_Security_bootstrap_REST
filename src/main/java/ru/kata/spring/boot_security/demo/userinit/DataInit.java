package ru.kata.spring.boot_security.demo.userinit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataInit {
    private final UserService userService;

    @Autowired
    public DataInit(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void initDbUsers() {
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");

        User admin = new User();
        Set<Role> adminRoles = new HashSet<>();
        Collections.addAll(adminRoles, adminRole);
        admin.setId(1);
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setName("Ерик");
        admin.setLastName("Сламбек");
        admin.setAge((byte) 30);
        admin.setRoles(adminRoles);
        userService.saveUser(admin);

        User user = new User();
        Set<Role> userRoles = new HashSet<>();
        Collections.addAll(userRoles, userRole);
        user.setId(2);
        user.setUsername("user");
        user.setPassword("user");
        user.setName("Серик");
        user.setLastName("Сламбеков");
        user.setAge((byte) 32);
        user.setRoles(userRoles);
        userService.saveUser(user);
    }
}
