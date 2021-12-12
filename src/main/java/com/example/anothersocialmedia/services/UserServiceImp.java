package com.example.anothersocialmedia.services;

import com.example.anothersocialmedia.dtos.FriendDTO;
import com.example.anothersocialmedia.dtos.FriendshipRequestDTO;
import com.example.anothersocialmedia.dtos.UserDTO;
import com.example.anothersocialmedia.dtos.UserInformationDTO;
import com.example.anothersocialmedia.entities.Friend;
import com.example.anothersocialmedia.entities.FriendshipRequest;
import com.example.anothersocialmedia.entities.User;
import com.example.anothersocialmedia.repos.FriendRepo;
import com.example.anothersocialmedia.repos.FriendshipRepo;
import com.example.anothersocialmedia.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

        List<FriendDTO> friendDTOS = new ArrayList<>();
        List<Friend> friendList = friendRepo.findAllByUsersId(userId);
        for (Friend f: friendList) {
            friendDTOS.add(new FriendDTO(f));
        }
        userInformationDTO.setFriends(friendDTOS);

        List<FriendshipRequestDTO> friendshipRequestDTOSent = new ArrayList<>();
        List<FriendshipRequest> friendshipRequestsSent = friendshipRepo.findAllByUsersIdAndType(userId,true);
        for (FriendshipRequest f: friendshipRequestsSent) {
            friendshipRequestDTOSent.add(new FriendshipRequestDTO(f));
        }
        userInformationDTO.setRequestsSent(friendshipRequestDTOSent);

        List<FriendshipRequestDTO> friendshipRequestDTOReceived = new ArrayList<>();
        List<FriendshipRequest> friendshipRequestsReceived = friendshipRepo.findAllByUsersIdAndType(userId,false);
        for (FriendshipRequest f: friendshipRequestsReceived) {
            friendshipRequestDTOReceived.add(new FriendshipRequestDTO(f));
        }
        userInformationDTO.setRequestsReceived(friendshipRequestDTOReceived);

        return userInformationDTO;
    }
}
