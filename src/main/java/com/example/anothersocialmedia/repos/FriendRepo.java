package com.example.anothersocialmedia.repos;

import com.example.anothersocialmedia.entities.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepo extends JpaRepository<Friend,Integer> {
}
