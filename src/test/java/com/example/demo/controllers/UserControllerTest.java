package com.example.demo.controllers;


import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserControllerTest {
    UserRepository mockedUserRepository = mock(UserRepository.class);

    UserController userController = new UserController(mockedUserRepository);

    @Test
    public void getUsers_handlesEmptyList() {
        // Given
        when(mockedUserRepository.getUsers(null))
                .thenReturn(List.of(
                ));

        // When
        ResponseEntity<List<User>> users = userController.getUsers(null);

        // Then
        Assertions.assertEquals(
                List.of(),
                users.getBody()
        );
    }

    @Test
    public void getUsers_returnsAllUsers() {
        // Given
        when(mockedUserRepository.getUsers(null))
                .thenReturn(List.of(
                        new User(1, "Bk", 20),
                        new User(2, "peppy", 27),
                        new User(2, "peppy", 25),
                        new User(2, "Bk", 1)
                ));

        // When
        ResponseEntity<List<User>> users = userController.getUsers(null);

        // Then
        Assertions.assertEquals(
                List.of(
                        new User(1, "Bk", 20),
                        new User(2, "peppy", 27),
                        new User(2, "peppy", 25),
                        new User(2, "Bk", 1)
                ),
                users.getBody()
        );
    }

    @Test
    public void getUsers_returnsBkUsers() {
        // Given
        when(mockedUserRepository.getUsers(null))
                .thenReturn(List.of(
                        new User(1, "Bk", 20),
                        new User(2, "peppy", 27),
                        new User(2, "peppy", 25),
                        new User(2, "Bk", 1)
                ));

        // When
        ResponseEntity<List<User>> users = userController.getUsers("Bk");

        // Then
        Assertions.assertEquals(
                List.of(
                        new User(1, "Bk", 20),
                        new User(2, "Bk", 1)
                ),
                users.getBody()
        );
    }

    @Test
    public void getUsers_handlesError() {
        // Given
        when(mockedUserRepository.getUsers(null))
                .thenThrow(new RuntimeException("service is down"));

        // When
        ResponseEntity<List<User>> response = userController.getUsers("Bk");

        // Then
        Assertions.assertEquals(
                HttpStatus.INTERNAL_SERVER_ERROR,
                response.getStatusCode()
        );
    }
}
