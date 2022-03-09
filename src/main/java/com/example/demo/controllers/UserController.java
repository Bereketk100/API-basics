package com.example.demo.controllers;
import com.example.demo.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    List<User> users = new ArrayList<User>();

    public UserController() {
        users.add(new User(1, "bk", 21));
        users.add(new User(2, "peppy", 27));
        users.add(new User(4, "Aman", 24));
    }

    @GetMapping()
    public List<User> getUsers(@RequestParam(name = "name", required = false) String name) {
        List<User> foundUsers = new ArrayList<>();
        if (name == null) {
            return users;
        }
        for (User user : users) {
            if (user.getName().equals(name)) {
                foundUsers.add(user);
            }
        }
        return foundUsers;
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable("id") int id) { // path variable is included in URL
        User foundUser = null;
        for (User user : users) {
            if (user.getId() == id) {
                foundUser = user;
            }
        }
        if (foundUser == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(foundUser, HttpStatus.OK);
    }

    @PostMapping// POST requests are used to send data to the API server to create
    public ResponseEntity createUser(@RequestBody User user) {
        if (user.getId() == 0 || user.getAge() == 0 || user.getName() == null) { // user information must be valid
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        for (User users : users) { // ID can not already exist
            if (users.getId() == user.getId()) {
                return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
            }
        }
        users.add(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping // PUT requests are used to send data to the API to update
    public ResponseEntity updateUser(@RequestBody User user) {
        if (user.getId() == 0 || user.getAge() == 0 || user.getName() == null) { // user information must be valid
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        for (User users : users) {
            if (users.getId() == user.getId()) {
                users.setName(user.getName());
                users.setAge(user.getAge());
            }
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }


    @DeleteMapping
    public ResponseEntity deleteUser(@RequestBody User user) { // delete user given the user object
        for(User u: users) {
            if (u.getId() == user.getId()) {
                users.remove(u);
                return new ResponseEntity(HttpStatus.ACCEPTED);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable("id") int id){ // delete by path using id as a variable
        for(User u: users) {
            if (u.getId() == id) {
                users.remove(u);
                return new ResponseEntity(HttpStatus.ACCEPTED);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }


}
