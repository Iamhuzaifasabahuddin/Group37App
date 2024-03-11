package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.FriendRequest;
import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.repo.FriendRequestRepository;
import com.example.group37software_engineering.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sound.midi.SysexMessage;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Controller
public class FriendController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendRequestRepository requestRepository;
    @RequestMapping("/friends")
    public String friends(Model model, Principal principal) {
        List<MyUser> friends = userRepository.findByUsername(principal.getName()).getFriends();
        List<MyUser> users = (List<MyUser>) userRepository.findAll();
        users.remove(userRepository.findByUsername(principal.getName()));
        users.removeAll(friends);
        List<FriendRequest> receiverRequests = requestRepository.findByReceiverUsername(principal.getName());
        List<FriendRequest> senderRequests = requestRepository.findBySenderUsername(principal.getName());
        users.removeAll(senderRequests.stream().map(FriendRequest::getReceiver).toList());
        users.removeAll(receiverRequests.stream().map(FriendRequest::getSender).toList());
        model.addAttribute("users", users);
        model.addAttribute("senderRequests", senderRequests);
        model.addAttribute("receiverRequests", receiverRequests);
        model.addAttribute("friends", friends);
        return "friends";
    }

    @RequestMapping ("/addFriend")
    public String addFriend(@RequestParam String receiver, Principal principal) {
        String sender = principal.getName();
        FriendRequest request = new FriendRequest();
        request.setSender(userRepository.findByUsername(sender));
        request.setReceiver(userRepository.findByUsername(receiver));
        requestRepository.save(request);
        return "redirect:/friends";
    }

    @RequestMapping ("/removeFriend")
    public String removeFriend(@RequestParam String receiverUsername, Principal principal) {
        String senderUsername = principal.getName();
        MyUser sender = userRepository.findByUsername(senderUsername);
        MyUser receiver = userRepository.findByUsername(receiverUsername);
        sender.getFriends().remove(receiver);
        receiver.getFriends().remove(sender);
        userRepository.saveAll(List.of(sender, receiver));
        return "redirect:/friends";
    }

    @RequestMapping("/handleRequest")
    public String handleRequest(@RequestParam String senderUsername, @RequestParam String decision, Principal principal) {
        String receiverUsername = principal.getName();
        FriendRequest request = requestRepository.findBySenderUsernameAndReceiverUsername(senderUsername, receiverUsername);
        requestRepository.delete(request);
        if (Objects.equals(decision, "accept")) {
            MyUser sender = userRepository.findByUsername(senderUsername);
            MyUser receiver = userRepository.findByUsername(receiverUsername);
            sender.getFriends().add(receiver);
            receiver.getFriends().add(sender);
            userRepository.saveAll(List.of(sender, receiver));
        }
        return "redirect:/friends";
    }


}
