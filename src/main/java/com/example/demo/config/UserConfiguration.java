package com.example.demo.config;

import com.example.demo.repositories.FakeUserRepository;
import com.example.demo.repositories.InMemoryUserRepository;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {
    @Bean
    public UserRepository inMemoryUserRepository() {
        final InMemoryUserRepository repo = new InMemoryUserRepository();

        repo.createUser(new User(1, "Bk", 20));
        repo.createUser(new User(2, "peppy", 27));

        return repo;
    }

//    @Bean
//    public UserRepository fakeRepo() {
//        final UserRepository repo = new FakeUserRepository();
//
//        return repo;
//    }
}
