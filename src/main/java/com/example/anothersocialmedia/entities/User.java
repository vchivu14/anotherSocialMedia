package com.example.anothersocialmedia.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Column(name = "password", nullable = false, length = 90)
    private String password;

}
