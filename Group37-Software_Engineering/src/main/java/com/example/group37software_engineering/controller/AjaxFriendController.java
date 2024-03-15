package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.FriendRequest;
import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.repo.FriendRequestRepository;
import com.example.group37software_engineering.repo.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@RestController
public class AjaxFriendController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendRequestRepository requestRepository;
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
            userRepository.saveAll(List.of(sender, receiver));
            return ResponseEntity.ok().body("{\"decision\": true}");
        }
        return ResponseEntity.ok().body("{\"decision\": false}");
    }

    @RequestMapping("/removeFriend")
    public ResponseEntity<?> removeFriend(@RequestParam String receiverUsername, Principal principal) {
        String senderUsername = principal.getName();
        MyUser sender = userRepository.findByUsername(senderUsername);
        MyUser receiver = userRepository.findByUsername(receiverUsername);
        sender.getFriends().remove(receiver);
        receiver.getFriends().remove(sender);
        userRepository.saveAll(List.of(sender, receiver));
        return ResponseEntity.ok().body("");
    }

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
        return ResponseEntity.ok().body("{\"friends\":" + gson.toJson(friends) + ", \"users\":" + gson.toJson(users) + ", \"senderRequests\":" + gson.toJson(senderRequests) + "}");
    }


}
