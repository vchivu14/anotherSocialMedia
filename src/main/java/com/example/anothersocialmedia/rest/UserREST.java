package com.example.anothersocialmedia.rest;

import com.example.anothersocialmedia.dtos.UserDTO;
import com.example.anothersocialmedia.entities.User;
import com.example.anothersocialmedia.services.UserServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class UserREST {
    private UserServiceImp userServiceImp;

    public UserREST(UserServiceImp userServiceImp){
        this.userServiceImp = userServiceImp;
    }

    @PostMapping("/login")
    private ResponseEntity<User> login (@RequestBody UserDTO user){
        return userServiceImp.login(user);
    }

    @PostMapping("/signup")
    private ResponseEntity<User> signup(@RequestBody User user){
        return userServiceImp.signup(user);
    }
}
