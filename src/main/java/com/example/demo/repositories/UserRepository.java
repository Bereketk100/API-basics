package com.example.demo.repositories;

import com.example.demo.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public interface UserRepository {
    // GET -> get all users or if name is passed get users with that name
    List<User> getUsers(String name);

    // GET -> get a user by id
    ResponseEntity getUserById(int id);

    // POST ->  create a user by making user object using JSON
   ResponseEntity createUser(User user);

    // PUT ->  update a user by passing updated user object using JSON
    ResponseEntity updateUser(User user);

    // DELETE - > delete a user by passing user object using JSON
    ResponseEntity deleteUser(User user);

    // DELETE - > delete a user by id
    ResponseEntity deleteUserById(int id);

}
