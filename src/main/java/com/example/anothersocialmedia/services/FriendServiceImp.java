package com.example.anothersocialmedia.services;

import com.example.anothersocialmedia.repos.FriendRepo;
import org.springframework.stereotype.Service;

@Service
public class FriendServiceImp implements FriendService {
    private FriendRepo friendRepo;

    public FriendServiceImp(FriendRepo friendRepo) {
        this.friendRepo = friendRepo;
    }
}
