package com.example.anothersocialmedia.repos;

import com.example.anothersocialmedia.entities.FriendshipRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendshipRepo extends JpaRepository<FriendshipRequest, Integer> {
    FriendshipRequest findByEmailAndHostAndUsersIdAndType(String email, String host, int userId, Boolean type);
}
