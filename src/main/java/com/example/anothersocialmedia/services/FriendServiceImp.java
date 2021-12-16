package com.example.anothersocialmedia.services;

import com.example.anothersocialmedia.entities.Friend;
import com.example.anothersocialmedia.repos.FriendRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImp implements FriendService {
    private FriendRepo friendRepo;

    public FriendServiceImp(FriendRepo friendRepo) {
        this.friendRepo = friendRepo;
    }

    @Override
    public List<Friend> findAllFriendsForUser(int userId) {
        return friendRepo.findAllByUsersId(userId);
    }
}
