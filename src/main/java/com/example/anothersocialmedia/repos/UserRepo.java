package com.example.anothersocialmedia.repos;

import com.example.anothersocialmedia.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
