package com.example.anothersocialmedia.rest;

import com.example.anothersocialmedia.dtos.UserDTO;
import com.example.anothersocialmedia.dtos.UserInformationDTO;
import com.example.anothersocialmedia.entities.User;
import com.example.anothersocialmedia.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class UserREST {
    private UserService userService;

    public UserREST(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserInformationDTO> getHomePage(@RequestParam Integer userId) {
        if (userId == null || userService.getById(userId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(userService.getUsersInformation(userId), HttpStatus.OK);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserDTO userDTO) {
        if (userService.login(userDTO) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(userService.login(userDTO), HttpStatus.OK);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user){
        return new ResponseEntity<>(userService.signup(user), HttpStatus.OK);
    }

}
