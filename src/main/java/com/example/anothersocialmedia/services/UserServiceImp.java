package com.example.anothersocialmedia.services;

import com.example.anothersocialmedia.dtos.UserDTO;
import com.example.anothersocialmedia.entities.User;
import com.example.anothersocialmedia.repos.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Override
    public User login(UserDTO user) {
        try {
            User dbUser = userRepo.findByEmail(user.getEmail());
            if (!dbUser.getPassword().equals(user.getPassword())) return null;
            else return dbUser;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public User signup(User user) {
        return userRepo.save(user);
    }

}
