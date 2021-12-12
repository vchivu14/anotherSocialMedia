package com.example.anothersocialmedia.services;

import com.example.anothersocialmedia.dtos.UserDTO;
import com.example.anothersocialmedia.dtos.UserInformationDTO;
import com.example.anothersocialmedia.entities.User;

public interface UserService {
    boolean getUserByEmail(String email);
    User login(UserDTO user);
    User signup(User user);
    UserInformationDTO getUsersInformation(int userId);
    User getById(int userId);
}
