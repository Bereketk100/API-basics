package com.example.demo.controllers;

import com.example.demo.models.InMemoryUserRepository;
import com.example.demo.models.User;
import com.example.demo.models.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    UserRepository repository;

    public UserController() {
        repository = new InMemoryUserRepository();
        repository.createUser(new User(1, "Bk", 20));
        repository.createUser(new User(2, "peppy", 27));
    }

    @GetMapping
    public List<User> getUsers(@RequestParam(name = "name", required = false) String name) {
        return repository.getUsers(name);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable("id") int id) {
        return repository.getUserById(id);
    }

    // @GetMapping("/{age}")
    // How would the http request know that its an age that i am looking for and not an ID
    @PostMapping
    public ResponseEntity createUser(@RequestBody User user) {
        return repository.createUser(user);
    }

    @PutMapping
    public ResponseEntity updateUser(@RequestBody User user) {
        return repository.updateUser(user);
    }

    @DeleteMapping
    public ResponseEntity deleteUser(@RequestBody User user) { // delete user given the user object
        return repository.deleteUser(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable("id") int id) { // delete by path using id as a variable
        return repository.deleteUserById(id);
    }
}






