package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.FriendRequest;
import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.repo.FriendRequestRepository;
import com.example.group37software_engineering.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.SysexMessage;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
/**
 * Controller class for managing friend-related operations.
 */
@Controller
public class FriendController {

    /**
     * Repository for accessing user data.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Repository for accessing friend request data.
     */
    @Autowired
    private FriendRequestRepository requestRepository;

    /**
     * Handles the "/friends" endpoint to display a user's friend requests and information.
     *
     * @param model     The model to which attributes are added for rendering the view.
     * @param principal The principal representing the currently authenticated user.
     * @return The view name for rendering the friend-related information.
     */
    @RequestMapping("/friends")
    public String friends(Model model, Principal principal) {
        List<FriendRequest> receiverRequests = requestRepository.findByReceiverUsername(principal.getName());
        model.addAttribute("receiverRequests", receiverRequests);
        model.addAttribute("user", userRepository.findByUsername(principal.getName()));
        return "Friends/friends";
    }

    /**
     * Handles the "friend-profile" endpoint to display information about a friend.
     *
     * @param model    The model to which attributes are added for rendering the view.
     * @param username The username of the friend whose profile is being viewed.
     * @param principal The principal representing the currently authenticated user.
     * @return The view name for rendering the friend's profile information.
     */
    @GetMapping("friend-profile")
    public String friendProfile(Model model, @RequestParam String username, Principal principal) {
        MyUser user = userRepository.findByUsername(username);
        MyUser loggedInUser = userRepository.findByUsername(principal.getName());
        int index = loggedInUser.getFriends().indexOf(user);
        LocalDateTime since = loggedInUser.getFriendsSince().get(index);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        String formattedDate = since.format(formatter);
        model.addAttribute("user", user);
        model.addAttribute("since", formattedDate);
        return "Friends/friend-profile";
    }
}
