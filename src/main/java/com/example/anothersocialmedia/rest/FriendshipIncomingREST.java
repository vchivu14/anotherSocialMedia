package com.example.anothersocialmedia.rest;

import com.example.anothersocialmedia.dtos.FriendshipProtocolRequest;
import com.example.anothersocialmedia.dtos.FriendshipProtocolResponse;
import com.example.anothersocialmedia.services.FriendshipService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/friendship")
public class FriendshipIncomingREST {
    private FriendshipService friendshipService;

    public FriendshipIncomingREST(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

    @PostMapping
    public ResponseEntity<FriendshipProtocolResponse> receiveFriendshipRequest
            (@RequestBody @Valid FriendshipProtocolRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            FriendshipProtocolResponse response = new FriendshipProtocolResponse(request.getVersion(), 503, "Wrong parameters!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            FriendshipProtocolResponse response = friendshipService.receiveFriendshipRequest(request);
            System.out.println(response.getPhrase());
            System.out.println(response.getStatus());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
