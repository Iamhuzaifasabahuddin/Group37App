package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.FriendRequest;
import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.repo.FriendRequestRepository;
import com.example.group37software_engineering.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        List<FriendRequest> receiverRequests = requestRepository.findByReceiverUsername(principal.getName());
        model.addAttribute("receiverRequests", receiverRequests);
        return "friends";
    }

}
