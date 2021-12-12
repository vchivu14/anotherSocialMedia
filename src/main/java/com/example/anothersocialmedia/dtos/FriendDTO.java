package com.example.anothersocialmedia.dtos;

import com.example.anothersocialmedia.entities.Friend;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FriendDTO {
    private String email;
    private String host;

    public FriendDTO(Friend friend) {
        this.email = friend.getEmail();
        this.host = friend.getHost();
    }
}
