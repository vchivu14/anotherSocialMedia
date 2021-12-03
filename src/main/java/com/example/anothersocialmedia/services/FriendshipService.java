package com.example.anothersocialmedia.services;

import com.example.anothersocialmedia.dtos.FriendshipProtocolRequest;
import com.example.anothersocialmedia.dtos.FriendshipProtocolResponse;

public interface FriendshipService {
    void saveSuccessfulFriendshipRequestResponse(FriendshipProtocolRequest friendshipProtocolRequest);
    FriendshipProtocolResponse receiveFriendshipRequest(FriendshipProtocolRequest friendshipProtocolRequest);
    FriendshipProtocolResponse checkSentFriendshipStatus(FriendshipProtocolRequest friendshipProtocolRequest);
    FriendshipProtocolResponse checkReceivedFriendshipStatus(FriendshipProtocolRequest friendshipProtocolRequest);
}
