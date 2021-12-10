package com.example.anothersocialmedia.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "friendship_request")
public class FriendshipRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Column(name = "host", nullable = false, length = 45)
    private String host;

    @Column(name = "Users_id", nullable = false)
    private int usersId;

    @Column(name = "status", nullable = false, length = 45)
    private String status;

    @Column(name = "type", nullable = false)
    private Boolean type;

    @CreationTimestamp
    @Column(name = "time", nullable = false)
    private Timestamp time;
}
