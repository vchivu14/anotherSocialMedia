package com.example.anothersocialmedia.services;

import com.example.anothersocialmedia.dtos.FriendshipProtocolRequest;
import com.example.anothersocialmedia.dtos.FriendshipProtocolResponse;

public interface FriendshipService {
    FriendshipProtocolResponse receiveFriendshipRequest(FriendshipProtocolRequest friendshipProtocolRequest);
    FriendshipProtocolResponse checkSentFriendshipStatus(FriendshipProtocolRequest friendshipProtocolRequest);
    FriendshipProtocolResponse solveMethodByResponse(FriendshipProtocolRequest request);
}
