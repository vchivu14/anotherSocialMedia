package com.example.anothersocialmedia.services;

import com.example.anothersocialmedia.dtos.FriendshipProtocolRequest;
import com.example.anothersocialmedia.dtos.FriendshipProtocolResponse;
import com.example.anothersocialmedia.entities.Friend;
import com.example.anothersocialmedia.entities.FriendshipRequest;
import com.example.anothersocialmedia.repos.FriendRepo;
import com.example.anothersocialmedia.repos.FriendshipRepo;
import com.example.anothersocialmedia.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class FriendshipServiceImp implements FriendshipService {
    private final static String methodAdd = "Add";
    private final static String methodAccept = "Accept";
    private final static String methodDeny = "Deny";
    private final static String methodRemove = "Remove";
    private final static String methodBlock = "Block";
    private final static String requestPending = "pending";
    private final static String requestDenied = "denied";

    private FriendshipRepo friendshipRepo;
    private FriendRepo friendRepo;
    private UserRepo userRepo;

    @Autowired
    UserService userService;

    public FriendshipServiceImp(FriendshipRepo friendshipRepo, FriendRepo friendRepo, UserRepo userRepo) {
        this.friendshipRepo = friendshipRepo;
        this.friendRepo = friendRepo;
        this.userRepo = userRepo;
    }

    // general helper method
    private String checkFriendshipProtocolMethod(String method) {
        return switch (method) {
            case methodAdd -> methodAdd;
            case methodDeny -> methodDeny;
            case methodAccept -> methodAccept;
            case methodRemove -> methodRemove;
            case methodBlock -> methodBlock;
            default -> null;
        };
    }

    // helper methods for receiving requests
    private boolean checkIfAlreadyFriends(FriendshipProtocolRequest friendshipProtocolRequest, int userId) {
        return friendRepo.findByEmailAndHostAndUsersId(friendshipProtocolRequest.getSender(),
                friendshipProtocolRequest.getSrcHost(), userId) != null;
    }
    private boolean checkIfFriendRequestWasSentAlready(FriendshipProtocolRequest friendshipProtocolRequest, int userId) {
        return friendshipRepo.findByEmailAndHostAndUsersIdAndType(friendshipProtocolRequest.getSender(),
                friendshipProtocolRequest.getSrcHost(), userId, false) != null;
    }

    private FriendshipProtocolResponse checkReceivedFriendshipStatus(FriendshipProtocolRequest friendshipProtocolRequest) {
        // user is receiver in the request, so he is the recipient here
        if (!userService.getUserByEmail(friendshipProtocolRequest.getRecipient())) {
            return new FriendshipProtocolResponse(friendshipProtocolRequest.getVersion(), 201, "No user here with this account!");
        }
        int userId = userRepo.findByEmail(friendshipProtocolRequest.getRecipient()).getId();
        if (checkIfAlreadyFriends(friendshipProtocolRequest, userId)) {
            return new FriendshipProtocolResponse(friendshipProtocolRequest.getVersion(), 201, "Already friends!");
        } else if (checkIfFriendRequestWasSentAlready(friendshipProtocolRequest, userId)) {
            return new FriendshipProtocolResponse(friendshipProtocolRequest.getVersion(), 201, "Friend Request already sent!");
        } else {
            return null;
        }
    }

    private FriendshipRequest getFriendshipRequestFromDTOWhenReceived(FriendshipProtocolRequest friendshipProtocolRequest) {
        FriendshipRequest friendshipRequest = new FriendshipRequest();
        friendshipRequest.setEmail(friendshipProtocolRequest.getSender());
        friendshipRequest.setHost(friendshipProtocolRequest.getSrcHost());
        return friendshipRequest;
    }

    private FriendshipProtocolResponse getMethodAddResponse(FriendshipProtocolRequest friendshipProtocolRequest) {
        // user is receiver in the request when receiving friendship requests
        // ergo we should find him in the attribute field recipient conform to our protocol
        // if not here error will be submitted
        if (checkReceivedFriendshipStatus(friendshipProtocolRequest) != null) {
            return checkReceivedFriendshipStatus(friendshipProtocolRequest);
        } else {
            int userId = userRepo.findByEmail(friendshipProtocolRequest.getRecipient()).getId();
            FriendshipRequest friendshipRequest = getFriendshipRequestFromDTOWhenReceived(friendshipProtocolRequest);
            // "pending", "denied"
            friendshipRequest.setStatus(requestPending);
            // true for SENT requests false for RECEIVED ones
            // in this case we RECEIVE a friendship request, so it's false type
            friendshipRequest.setType(false);
            friendshipRequest.setUsersId(userId);
            friendshipRepo.save(friendshipRequest);
            return new FriendshipProtocolResponse(friendshipProtocolRequest.getVersion(), 200, "Success");
        }
    }

    private FriendshipProtocolResponse getMethodAcceptResponse(FriendshipProtocolRequest friendshipProtocolRequest) {
        if (checkReceivedFriendshipStatus(friendshipProtocolRequest) != null) {
            return checkReceivedFriendshipStatus(friendshipProtocolRequest);
        } else {
            int userId = userRepo.findByEmail(friendshipProtocolRequest.getRecipient()).getId();
            String friendEmail = friendshipProtocolRequest.getSender();
            System.out.println(friendEmail);
            String friendHost = friendshipProtocolRequest.getSrcHost();
            System.out.println(friendHost);
            FriendshipRequest friendshipRequest = friendshipRepo.findByEmailAndHostAndUsersIdAndType(friendEmail,friendHost,userId,true);
            friendRepo.save(new Friend(friendEmail,friendHost,userId));
            friendshipRepo.deleteById(friendshipRequest.getId());
            return new FriendshipProtocolResponse(friendshipProtocolRequest.getVersion(), 200, "You are now friends!");
        }
    }

    private FriendshipProtocolResponse getMethodDenyResponse(FriendshipProtocolRequest friendshipProtocolRequest) {
        if (checkReceivedFriendshipStatus(friendshipProtocolRequest) != null) {
            return checkReceivedFriendshipStatus(friendshipProtocolRequest);
        } else {
            int userId = userRepo.findByEmail(friendshipProtocolRequest.getRecipient()).getId();
            String friendEmail = friendshipProtocolRequest.getSender();
            System.out.println(friendEmail);
            String friendHost = friendshipProtocolRequest.getSrcHost();
            System.out.println(friendHost);
            FriendshipRequest friendshipRequest = friendshipRepo.findByEmailAndHostAndUsersIdAndType(friendEmail,friendHost,userId,true);
            friendshipRequest.setStatus(requestDenied);
            friendshipRepo.save(friendshipRequest);
            return new FriendshipProtocolResponse(friendshipProtocolRequest.getVersion(), 200, "You have denied the friend request!");
        }
    }

    private FriendshipProtocolResponse getMethodRemoveResponse(FriendshipProtocolRequest friendshipProtocolRequest) {
        return new FriendshipProtocolResponse(friendshipProtocolRequest.getVersion(), 500, "Method not yet implemented");

    }

    private FriendshipProtocolResponse getMethodBlockResponse(FriendshipProtocolRequest friendshipProtocolRequest) {
        return new FriendshipProtocolResponse(friendshipProtocolRequest.getVersion(), 500, "Method not yet implemented");
    }

    private FriendshipProtocolResponse getMethodResponseForReceivedProtocol(FriendshipProtocolRequest friendshipProtocolRequest) {
        FriendshipProtocolResponse friendshipProtocolResponse;
        String method = checkFriendshipProtocolMethod(friendshipProtocolRequest.getMethod());
        friendshipProtocolResponse = switch (method) {
            case methodAdd -> getMethodAddResponse(friendshipProtocolRequest);
            case methodAccept -> getMethodAcceptResponse(friendshipProtocolRequest);
            case methodDeny -> getMethodDenyResponse(friendshipProtocolRequest);
            case methodRemove -> getMethodRemoveResponse(friendshipProtocolRequest);
            case methodBlock -> getMethodBlockResponse(friendshipProtocolRequest);
            default -> new FriendshipProtocolResponse(friendshipProtocolRequest.getVersion(), 500, "Method doesn't exist");
        };
        return friendshipProtocolResponse;
    }

    @Value("#{environment.OUR_SERVER}")
    private String this_social_media;

    @Override
    public FriendshipProtocolResponse receiveFriendshipRequest(FriendshipProtocolRequest friendshipProtocolRequest) {
        System.out.println("Version: "+friendshipProtocolRequest.getVersion());
        System.out.println("Method: "+friendshipProtocolRequest.getMethod());
        System.out.println("Sender: "+friendshipProtocolRequest.getSender());
        System.out.println("Sender Host: "+friendshipProtocolRequest.getSrcHost());
        System.out.println("Receiver: "+friendshipProtocolRequest.getRecipient());
        System.out.println("Receiver Host: "+friendshipProtocolRequest.getRcpHost());
        FriendshipProtocolResponse friendshipProtocolResponse;
        System.out.println("this server: " + this_social_media);
        System.out.println("other server: " + friendshipProtocolRequest.getSrcHost());
        if (friendshipProtocolRequest.getRcpHost().equals(this_social_media)) {
            // this should be an interface to decide on whatever version different answer
            if (friendshipProtocolRequest.getVersion() == 1) {
                // method check based on version?
                friendshipProtocolResponse = getMethodResponseForReceivedProtocol(friendshipProtocolRequest);
            } else {
                friendshipProtocolResponse = new FriendshipProtocolResponse(friendshipProtocolRequest.getVersion(), 500, "Version Protocol doesn't correspond");
            }
        } else {
            friendshipProtocolResponse = new FriendshipProtocolResponse(friendshipProtocolRequest.getVersion(), 500, "The host doesn't correspond");
        }
        return friendshipProtocolResponse;
    }

    // helper methods for sending requests
    // type of friendship requests are true as a business decision to split them like that
    private boolean sentProtocolCheckIfAlreadyFriends(FriendshipProtocolRequest friendshipProtocolRequest, int userId) {
        return friendRepo.findByEmailAndHostAndUsersId(friendshipProtocolRequest.getRecipient(),
                friendshipProtocolRequest.getRcpHost(), userId) != null;
    }
    private boolean sentProtocolCheckIfFriendRequestExistsAlready(FriendshipProtocolRequest friendshipProtocolRequest, int userId) {
        return friendshipRepo.findByEmailAndHostAndUsersIdAndType(friendshipProtocolRequest.getRecipient(),
                friendshipProtocolRequest.getRcpHost(), userId, true) != null;
    }
    private boolean sentProtocolCheckIfFriendRequestWasDenied(FriendshipProtocolRequest friendshipProtocolRequest, int userId) {
        return friendshipRepo.findByEmailAndHostAndUsersIdAndType(friendshipProtocolRequest.getRecipient(),
                friendshipProtocolRequest.getRcpHost(), userId, true) == null;
    }

    private FriendshipRequest getFriendshipRequestFromDTOWhenSending(FriendshipProtocolRequest friendshipProtocolRequest) {
        FriendshipRequest friendshipRequest = new FriendshipRequest();
        friendshipRequest.setEmail(friendshipProtocolRequest.getRecipient());
        friendshipRequest.setHost(friendshipProtocolRequest.getRcpHost());
        return friendshipRequest;
    }

    @Override
    public FriendshipProtocolResponse checkSentFriendshipStatus(FriendshipProtocolRequest friendshipProtocolRequest) {
        // user is sender in the request when sending friendship requests
        int userId = userRepo.findByEmail(friendshipProtocolRequest.getSender()).getId();
        if (sentProtocolCheckIfAlreadyFriends(friendshipProtocolRequest, userId)) {
            return new FriendshipProtocolResponse(friendshipProtocolRequest.getVersion(), 201, "Already friends!");
        } else if (sentProtocolCheckIfFriendRequestExistsAlready(friendshipProtocolRequest, userId)) {
            return new FriendshipProtocolResponse(friendshipProtocolRequest.getVersion(), 201, "Friend Request already sent!");
        } else {
            return null;
        }
    }

    private FriendshipProtocolResponse saveSuccessfulFriendshipRequestResponse(FriendshipProtocolRequest friendshipProtocolRequest) {
        // user is sender in the request when sending friendship requests
        System.out.println(friendshipProtocolRequest.getRecipient());
        System.out.println(friendshipProtocolRequest.getRcpHost());
        System.out.println(friendshipProtocolRequest.getSender());
        System.out.println(friendshipProtocolRequest.getSrcHost());
        int userId = userRepo.findByEmail(friendshipProtocolRequest.getSender()).getId();
        FriendshipRequest friendshipRequest = getFriendshipRequestFromDTOWhenSending(friendshipProtocolRequest);
        // "pending", "denied"
        friendshipRequest.setStatus(requestPending);
        // true for SENT requests false for RECEIVED ones
        // in this case we SEND a friendship request, so it's true type
        friendshipRequest.setType(true);
        friendshipRequest.setUsersId(userId);
        friendshipRepo.save(friendshipRequest);
        return new FriendshipProtocolResponse(friendshipProtocolRequest.getVersion(), 200, "Success! Friend request was sent");
    }

    private FriendshipProtocolResponse saveSuccessfulFriendshipAcceptedResponse(FriendshipProtocolRequest friendshipProtocolRequest) {
        int userId = userRepo.findByEmail(friendshipProtocolRequest.getSender()).getId();
        String friendEmail = friendshipProtocolRequest.getRecipient();
        String friendHost = friendshipProtocolRequest.getRcpHost();
        FriendshipRequest friendshipRequest = friendshipRepo.findByEmailAndHostAndUsersIdAndType(friendEmail,friendHost,userId,false);
        friendRepo.save(new Friend(friendEmail,friendHost,userId));
        friendshipRepo.deleteById(friendshipRequest.getId());
        return new FriendshipProtocolResponse(friendshipProtocolRequest.getVersion(), 200, "You are now friends!");
    }

    private FriendshipProtocolResponse saveSuccessfulFriendshipDeniedResponse(FriendshipProtocolRequest friendshipProtocolRequest) {
        int userId = userRepo.findByEmail(friendshipProtocolRequest.getSender()).getId();
        if (sentProtocolCheckIfFriendRequestWasDenied(friendshipProtocolRequest, userId)) {
            return new FriendshipProtocolResponse(friendshipProtocolRequest.getVersion(), 201, "Friend Request doesn't exist");
        }
        String friendEmail = friendshipProtocolRequest.getRecipient();
        String friendHost = friendshipProtocolRequest.getRcpHost();
        FriendshipRequest friendshipRequest = friendshipRepo.findByEmailAndHostAndUsersIdAndType(friendEmail,friendHost,userId,false);
        friendshipRepo.deleteById(friendshipRequest.getId());
        return new FriendshipProtocolResponse(friendshipProtocolRequest.getVersion(), 200, "You denied the friendship!");
    }

    @Override
    public FriendshipProtocolResponse solveMethodByResponse(FriendshipProtocolRequest request) {
        String method = checkFriendshipProtocolMethod(request.getMethod());
        return switch (method) {
            case methodAdd -> saveSuccessfulFriendshipRequestResponse(request);
            case methodAccept -> saveSuccessfulFriendshipAcceptedResponse(request);
            case methodDeny -> saveSuccessfulFriendshipDeniedResponse(request);
            case methodRemove, methodBlock -> new FriendshipProtocolResponse(request.getVersion(), 500, "Method not implemented!");
            default -> null;
        };
    }
}