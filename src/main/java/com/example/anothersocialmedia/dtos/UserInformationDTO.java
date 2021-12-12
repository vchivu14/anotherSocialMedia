package com.example.anothersocialmedia.dtos;

import com.example.anothersocialmedia.entities.Friend;
import com.example.anothersocialmedia.entities.FriendshipRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInformationDTO {
    List<Friend> friends;
    List<FriendshipRequest> requestsSent;
    List<FriendshipRequest> requestsReceived;
}
