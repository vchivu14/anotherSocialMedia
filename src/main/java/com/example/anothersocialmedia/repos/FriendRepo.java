package com.example.anothersocialmedia.repos;

import com.example.anothersocialmedia.entities.Friend;
import com.example.anothersocialmedia.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepo extends JpaRepository<Friend,Integer> {
    Friend findByEmailAndHostAndUser(String email, String host, User user);
}
