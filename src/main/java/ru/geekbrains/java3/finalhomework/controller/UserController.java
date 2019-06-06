package ru.geekbrains.java3.finalhomework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.java3.finalhomework.entity.User;
import ru.geekbrains.java3.finalhomework.repository.UserRepository;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", repository.findAll());
        return "index";
    }

    @GetMapping("adduser")
    public String addUserPage(User user, Model model) {
        model.addAttribute("adduser", true);
        return "add_user";
    }

    @PostMapping("adduser")
    public String addUser(User user) {
        repository.save(user);
        return "redirect:/";
    }

    @GetMapping("user/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/";
    }

    // не понимаю почему не подгружается бутстрап
    @GetMapping("user/{id}/edit")
    public String editUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", repository.findById(id));
        return "add_user";
    }

    @PostMapping("user/{id}/edit")
    public String editUser(User user) {
        repository.save(user);
        return "redirect:/";
    }


}
