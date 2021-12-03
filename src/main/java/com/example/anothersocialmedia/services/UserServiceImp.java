package com.example.anothersocialmedia.services;

import com.example.anothersocialmedia.repos.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    private UserRepo userRepo;

    public UserServiceImp(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public boolean getUserByEmail(String email) {
        return userRepo.findByEmail(email) != null;
    }
}
