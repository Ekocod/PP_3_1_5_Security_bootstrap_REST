package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/admin")
    public String getAdminPanel(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @GetMapping(value = "/create-new-user")
    public String getUserFormForCreate(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", roleService.getRoles());
        return "create-new-user";
    }

    @PostMapping("/submit")
    public String createUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";

    }

    @GetMapping("/update-user/{id}")
    public String getUserFormForUpdate(@PathVariable() int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getRoles());
        return "update-user";
    }

    @PatchMapping("/submit/{id}")
    public String updateUser(@ModelAttribute("user") User user, int id) {
        userService.updateUser(user, id);
        return "redirect:/admin";
    }

    @DeleteMapping("delete-user/{id}")
    public String deleteUser(@PathVariable() int id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }
}
