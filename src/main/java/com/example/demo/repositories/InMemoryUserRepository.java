package com.example.demo.repositories;

import com.example.demo.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUserRepository implements UserRepository {
    List<User> users = new ArrayList<>();

    public InMemoryUserRepository() {
    }

    @Override
    public List<User> getUsers(String name) {
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

    @Override
    public ResponseEntity getUserById(int id) {
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

    @Override
    public ResponseEntity createUser(User user) {
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

    @Override
    public ResponseEntity updateUser(User user) {
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

    @Override
    public ResponseEntity deleteUser(User user) {
        for (User u : users) {
            if (u.getId() == user.getId()) {
                users.remove(u);
                return new ResponseEntity(HttpStatus.ACCEPTED);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    public ResponseEntity deleteUserById(int id) {
        for (User u : users) {
            if (u.getId() == id) {
                users.remove(u);
                return new ResponseEntity(HttpStatus.ACCEPTED);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }
}
