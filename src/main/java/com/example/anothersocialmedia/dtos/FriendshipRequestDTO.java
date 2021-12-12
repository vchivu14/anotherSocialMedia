package com.example.anothersocialmedia.dtos;

import com.example.anothersocialmedia.entities.FriendshipRequest;
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
public class FriendshipRequestDTO {
    private String email;
    private String host;
    private String status;

    public FriendshipRequestDTO(FriendshipRequest friendshipRequest) {
        this.email = friendshipRequest.getEmail();
        this.host = friendshipRequest.getHost();
        this.status = friendshipRequest.getStatus();
    }
}
