package ru.geekbrains.java3.finalhomework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.java3.finalhomework.entity.User;
import ru.geekbrains.java3.finalhomework.repository.UserRepository;

import java.util.Optional;

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

    @PostMapping("adduser")
    @ResponseBody
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = repository.save(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("user/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/";
    }

    @PutMapping("edituser")
    @ResponseBody
    public ResponseEntity<User> editUser(@RequestBody User user) {
        repository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("getuser/{id}")
    @ResponseBody
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = repository.findById(id);
        return user.map(
                value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)
                );

    }


}
