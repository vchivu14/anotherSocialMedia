package com.example.anothersocialmedia.configuration;

import com.example.anothersocialmedia.entities.User;
import com.example.anothersocialmedia.repos.UserRepo;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {
    private UserRepo userRepo;

    public DataLoader(UserRepo userRepo) {
        this.userRepo = userRepo;
        if (userRepo.count() == 0) {
            loadUser();
        }
    }

    private void loadUser() {
        userRepo.save(new User("chivu", "hola"));
        userRepo.save(new User("vlad", "hola"));
        userRepo.save(new User("adam", "hola"));
    }
}
