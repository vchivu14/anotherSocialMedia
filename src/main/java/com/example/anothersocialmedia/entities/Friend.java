package com.example.anothersocialmedia.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "friends")
public class Friend {
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

}
