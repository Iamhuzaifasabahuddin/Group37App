package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.FriendRequest;
import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.repo.FriendRequestRepository;
import com.example.group37software_engineering.repo.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RestController
public class AjaxFriendController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendRequestRepository requestRepository;

    /**
     * Handles the "/sendRequest" endpoint to send a friend request.
     *
     * @param receiverUsername The username of the user to whom the friend request is sent.
     * @param principal        The principal representing the currently authenticated user.
     * @return ResponseEntity indicating the success of the request.
     */
    @RequestMapping("/sendRequest")
    public ResponseEntity<?> sendRequest(@RequestParam String receiverUsername, Principal principal) {
        MyUser user = userRepository.findByUsername(receiverUsername);
        if (user != null) {
            String senderUsername = principal.getName();
            FriendRequest request = new FriendRequest();
            if (requestRepository.findBySenderUsernameAndReceiverUsername(senderUsername, receiverUsername) == null) {
                request.setSender(userRepository.findByUsername(senderUsername));
                request.setReceiver(userRepository.findByUsername(receiverUsername));
                requestRepository.save(request);
            }
        }
        return ResponseEntity.ok().body("");
    }

    /**
     * Handles the "/handleRequest" endpoint to handle a friend request (accept or reject).
     *
     * @param senderUsername The username of the user who sent the friend request.
     * @param decision       The decision of the user regarding the friend request (accept or reject).
     * @param principal      The principal representing the currently authenticated user.
     * @return ResponseEntity indicating the success of the request.
     */
    @RequestMapping("/handleRequest")
    public ResponseEntity<?> handleRequest(@RequestParam String senderUsername, @RequestParam String decision, Principal principal) {
        String receiverUsername = principal.getName();
        FriendRequest request = requestRepository.findBySenderUsernameAndReceiverUsername(senderUsername, receiverUsername);
        requestRepository.delete(request);
        if (Objects.equals(decision, "accept")) {
            MyUser sender = userRepository.findByUsername(senderUsername);
            MyUser receiver = userRepository.findByUsername(receiverUsername);
            sender.getFriends().add(receiver);
            receiver.getFriends().add(sender);
            sender.getFriendsSince().add(LocalDateTime.now());
            receiver.getFriendsSince().add(LocalDateTime.now());
            userRepository.saveAll(List.of(sender, receiver));
            return ResponseEntity.ok().body("{\"decision\": true}");
        }
        return ResponseEntity.ok().body("{\"decision\": false}");
    }

    /**
     * Handles the "/removeFriend" endpoint to remove a friend.
     *
     * @param receiverUsername The username of the friend to be removed.
     * @param principal        The principal representing the currently authenticated user.
     * @return ResponseEntity indicating the success of the request.
     */
    @RequestMapping("/removeFriend")
    public ResponseEntity<?> removeFriend(@RequestParam String receiverUsername, Principal principal) {
        String senderUsername = principal.getName();
        MyUser sender = userRepository.findByUsername(senderUsername);
        MyUser receiver = userRepository.findByUsername(receiverUsername);
        int senderIndex = receiver.getFriends().indexOf(sender);
        int receiverIndex = sender.getFriends().indexOf(receiver);
        sender.getFriendsSince().remove(receiverIndex);
        receiver.getFriendsSince().remove(senderIndex);
        sender.getFriends().remove(receiver);
        receiver.getFriends().remove(sender);
        userRepository.saveAll(List.of(sender, receiver));
        return ResponseEntity.ok().body("");
    }

    /**
     * Handles the "/getFriends" endpoint to retrieve information about friends and potential friends.
     *
     * @param principal The principal representing the currently authenticated user.
     * @return ResponseEntity containing JSON data about friends, potential friends, and friend requests.
     */
    @RequestMapping("/getFriends")
    public ResponseEntity<?> getFriends(Principal principal) {
        List<MyUser> friends = userRepository.findByUsername(principal.getName()).getFriends();
        List<MyUser> users = (List<MyUser>) userRepository.findAll();
        users.remove(userRepository.findByUsername(principal.getName()));
        users.removeAll(friends);
        List<FriendRequest> receiverRequests = requestRepository.findByReceiverUsername(principal.getName());
        List<FriendRequest> senderRequests = requestRepository.findBySenderUsername(principal.getName());
        users.removeAll(senderRequests.stream().map(FriendRequest::getReceiver).toList());
        users.removeAll(receiverRequests.stream().map(FriendRequest::getSender).toList());
        Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

        List<MyUser> usersAll = (List<MyUser>) userRepository.findAll();
        Dictionary<String, List<String>> mutual = new Hashtable<>();
        for (MyUser user: usersAll) {
            List<MyUser> userFriends = userRepository.findByUsername(user.getUsername()).getFriends();
            List<String> tempList = new ArrayList<>();
            for (MyUser friend: friends) {
                if (userFriends.contains(friend)) {
                    tempList.add(friend.getUsername());
                }
            }
            mutual.put(user.getUsername(), tempList);
        }

        return ResponseEntity.ok().body("{\"friends\":" + gson.toJson(friends) + ", \"users\":" + gson.toJson(users) + ", \"senderRequests\":" + gson.toJson(senderRequests) + ", \"mutual\":" + gson.toJson(mutual) + "}");
    }

    @GetMapping("/SearchFriends")
    public ResponseEntity<?> searchFriends(@RequestParam String search, Principal principal) {
        MyUser user = userRepository.findByUsername(principal.getName());
        List<MyUser> friends = user.getFriends();
        List<MyUser> users = userRepository.findByUsernameContaining(search);
        users.removeAll(friends);
        users.remove(user);
        List<MyUser> usersAll = (List<MyUser>) userRepository.findAll();
        Dictionary<String, List<String>> mutual = new Hashtable<>();
        for (MyUser MutualUser: usersAll) {
            List<MyUser> userFriends = userRepository.findByUsername(MutualUser.getUsername()).getFriends();
            List<String> tempList = new ArrayList<>();
            for (MyUser friend: friends) {
                if (userFriends.contains(friend)) {
                    tempList.add(friend.getUsername());
                }
            }
            mutual.put(MutualUser.getUsername(), tempList);
        }
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        if (!users.isEmpty()) {
            return ResponseEntity.ok().body("{\"search\":" + gson.toJson(users) + "," + "\"mutual\":" + gson.toJson(mutual) + "}");
        }
        return ResponseEntity.ok().body("{\"search\": []}");
    }

}
