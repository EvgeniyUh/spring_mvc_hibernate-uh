package ru.uh.controller;

import ru.uh.models.User;
import ru.uh.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "users/index";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute(new User());
        return "users/create";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user, Model model) {
        userService.add(user);
        model.addAttribute("user", user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.remove(id);
        return "redirect:/users";
    }
}
