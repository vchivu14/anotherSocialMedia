package com.example.anothersocialmedia.services;

import com.example.anothersocialmedia.dtos.UserDTO;
import com.example.anothersocialmedia.dtos.UserInformationDTO;
import com.example.anothersocialmedia.entities.User;
import com.example.anothersocialmedia.repos.FriendRepo;
import com.example.anothersocialmedia.repos.FriendshipRepo;
import com.example.anothersocialmedia.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    private UserRepo userRepo;

    @Autowired
    FriendRepo friendRepo;
    @Autowired
    FriendshipRepo friendshipRepo;

    public UserServiceImp(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User getById(int userId) {
        return userRepo.getById(userId);
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

    @Override
    public UserInformationDTO getUsersInformation(int userId) {
        UserInformationDTO userInformationDTO = new UserInformationDTO();
        userInformationDTO.setFriends(friendRepo.findAllByUsersId(userId));
        userInformationDTO.setRequestsSent(friendshipRepo.findAllByUsersIdAndType(userId,true));
        userInformationDTO.setRequestsSent(friendshipRepo.findAllByUsersIdAndType(userId,false));
        return userInformationDTO;
    }
}
