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

    public ResponseEntity<User> login(UserDTO user) {
        User dbUser = userRepo.findByEmail(user.getEmail());
        System.out.println(dbUser);
        //we do not have user in db
        if(dbUser == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        //passwords are not mathing
        if(!dbUser.getPassword().equals(user.getPassword())) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(dbUser,HttpStatus.OK);
    }

    public ResponseEntity<User> signup(User user) {
        return new ResponseEntity<User>(userRepo.save(user),HttpStatus.OK);
    }
}
