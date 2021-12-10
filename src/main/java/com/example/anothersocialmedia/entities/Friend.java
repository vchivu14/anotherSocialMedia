package com.example.anothersocialmedia.entities;

import lombok.*;

import javax.persistence.*;

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

    public Friend(String email, String host, int usersId) {
        this.email = email;
        this.host = host;
        this.usersId = usersId;
    }
}
