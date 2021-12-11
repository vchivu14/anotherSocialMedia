package com.example.anothersocialmedia.services;

import com.example.anothersocialmedia.entities.Friend;

import java.util.List;

public interface FriendService {
    List<Friend> findAllFriendsForUser(int userId);
}
