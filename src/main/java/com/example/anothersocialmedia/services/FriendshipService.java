package com.example.anothersocialmedia.services;

import com.example.anothersocialmedia.dtos.FriendshipProtocolRequest;
import com.example.anothersocialmedia.dtos.FriendshipProtocolResponse;
import com.example.anothersocialmedia.entities.FriendshipRequest;

import java.util.List;

public interface FriendshipService {
    FriendshipProtocolResponse receiveFriendshipRequest(FriendshipProtocolRequest friendshipProtocolRequest);
    FriendshipProtocolResponse checkSentFriendshipStatus(FriendshipProtocolRequest friendshipProtocolRequest);
    FriendshipProtocolResponse solveMethodByResponse(FriendshipProtocolRequest request);
}
