package com.example.anothersocialmedia.rest;

import com.example.anothersocialmedia.dtos.FriendshipProtocolRequest;
import com.example.anothersocialmedia.dtos.FriendshipProtocolResponse;
import com.example.anothersocialmedia.services.FriendshipService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/response")
public class FriendshipRequesting {
    final String SERVER_B_API = "http://localhost:8081/api/friendship";

    private FriendshipService friendshipService;

    public FriendshipRequesting(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

    @PostMapping
    private ResponseEntity<FriendshipProtocolResponse> sendFriendshipRequest
            (@RequestBody FriendshipProtocolRequest request) {
        if (friendshipService.checkSentFriendshipStatus(request).getVersion() != null) {
            return new ResponseEntity<>(friendshipService.checkSentFriendshipStatus(request),HttpStatus.OK);
        } else {
            WebClient webClient = WebClient.builder()
                    .baseUrl(SERVER_B_API)
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .build();

            FriendshipProtocolResponse response = webClient.post()
                    .body(Mono.just(request), FriendshipProtocolRequest.class)
                    .retrieve()
                    .bodyToMono(FriendshipProtocolResponse.class)
                    .block();

            if (response != null) {
                if (response.getStatus() == 200) {
                    friendshipService.saveSuccessfulFriendshipRequestResponse(request);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
