package com.example.anothersocialmedia.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id",referencedColumnName = "id")
    @JsonIgnoreProperties("friends")
    private User user_id;

}
