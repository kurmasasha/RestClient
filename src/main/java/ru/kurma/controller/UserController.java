package ru.kurma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kurma.model.Role;
import ru.kurma.model.User;
import ru.kurma.service.RoleService;
import ru.kurma.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public String home() {
        return "redirect:/home";
    }

    @GetMapping("home")
    public String index(Model model) {
        model.addAttribute("userList",userService.getUserList());

        return "index";
    }

    @PostMapping("add")
    public String createNewUser(@RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam String login,
                             @RequestParam String password) {
        userService.addUser(firstName, lastName, login, password);
        return "redirect:/home";
    }

    @PostMapping("edit")
    public String editUser(@RequestParam String id,
                           @RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String login,
                           @RequestParam String password,
                           @RequestParam String role) {
        User user = userService.getUserById(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setLogin(login);
        user.setPassword(password);
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleById(role));
        user.setRoles(roles);
        userService.editUser(user);

        return "redirect:/home";
    }

    @PostMapping("delete")
    public String deleteUser(@RequestParam String id) {
        userService.deleteUser(id);
        return "redirect:/home";
    }

}
