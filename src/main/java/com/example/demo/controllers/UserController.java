package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    UserRepository repository;

    // Dependency injection - DI
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers(@RequestParam(name = "name", required = false) String name) {
        try {
            return new ResponseEntity(
                    repository.getUsers(null)
                            .stream()
                            .filter(user -> name == null || user.getName().equals(name))
                            .toList(),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity(
                    Map.of(
                            "message", "There was an internal server error. Please try again",
                            "status", HttpStatus.INTERNAL_SERVER_ERROR
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
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






