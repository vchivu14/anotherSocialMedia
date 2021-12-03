package com.example.anothersocialmedia.rest;

import com.example.anothersocialmedia.dtos.FriendshipProtocolRequest;
import com.example.anothersocialmedia.dtos.FriendshipProtocolResponse;
import com.example.anothersocialmedia.services.FriendshipService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/friendship")
public class FriendshipIncoming {
    private FriendshipService friendshipService;

    public FriendshipIncoming(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

    @PostMapping
    public ResponseEntity<FriendshipProtocolResponse> receiveFriendshipRequest
            (@RequestBody FriendshipProtocolRequest request) {
        FriendshipProtocolResponse response = friendshipService.receiveFriendshipRequest(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
