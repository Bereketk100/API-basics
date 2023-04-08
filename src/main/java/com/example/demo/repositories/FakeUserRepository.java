package com.example.demo.repositories;

import com.example.demo.models.User;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FakeUserRepository implements UserRepository {
    @Override
    public List<User> getUsers(String name) {
        return Collections.emptyList();
    }

    @Override
    public ResponseEntity getUserById(int id) {
        return null;
    }

    @Override
    public ResponseEntity createUser(User user) {
        return null;
    }

    @Override
    public ResponseEntity updateUser(User user) {
        return null;
    }

    @Override
    public ResponseEntity deleteUser(User user) {
        return null;
    }

    @Override
    public ResponseEntity deleteUserById(int id) {
        return null;
    }
}
