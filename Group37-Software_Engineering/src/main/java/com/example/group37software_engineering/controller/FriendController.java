package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.FriendRequest;
import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.model.UserAchievement;
import com.example.group37software_engineering.repo.AchievementRepository;
import com.example.group37software_engineering.repo.FriendRequestRepository;
import com.example.group37software_engineering.repo.UserCourseRepository;
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
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Controller class for managing friend-related operations.
 */
@Controller
public class FriendController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendRequestRepository requestRepository;

    @Autowired
    private AchievementController achievementController;

    @Autowired
    private UserCourseRepository userCourseRepository;

    @Autowired
    private AchievementRepository achievementRepository;

    @Autowired
    private MainController mainController;

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
        achievementController.DoubleTrouble(userRepository.findByUsername(principal.getName()));
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
    @GetMapping("/friend-profile")
    public String friendProfile(Model model, @RequestParam String username, Principal principal) {
        MyUser friend = userRepository.findByUsername(username);
        MyUser user = userRepository.findByUsername(principal.getName());
        int index = user.getFriends().indexOf(friend);
        LocalDateTime since = user.getFriendsSince().get(index);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        String formattedDate = since.format(formatter);

        model.addAttribute("friend", friend);
        model.addAttribute("DateJoined", friend.getDateJoined());
        model.addAttribute("user", user);
        model.addAttribute("since", formattedDate);
        model.addAttribute("Achievements", friend.getUserAchievements().size());
        model.addAttribute("TotalAchievements", achievementRepository.count());

        model.addAttribute("Courses_taken", userCourseRepository.findByUser(friend).size());
        model.addAttribute("LoggedInUser_Courses_taken", userCourseRepository.findByUser(user).size());

        model.addAttribute("Completed", mainController.countCompleted(friend.getUsername()));
        model.addAttribute("LoggedInUser_Completed", mainController.countCompleted(user.getUsername()));

        model.addAttribute("Percentage", mainController.hoursCompleted(friend.getUsername()));
        model.addAttribute("LoggedInUser_Percentage", mainController.hoursCompleted(user.getUsername()));

        model.addAttribute("Hours", mainController.hoursLeft(friend.getUsername()));
        model.addAttribute("LoggedInUser_Hours", mainController.hoursLeft(user.getUsername()));

        Integer rank = mainController.getRank(friend.getUsername());
        if (rank != null) {
            model.addAttribute("Rank", mainController.addRankSuffix(rank));
        } else {
            model.addAttribute("Rank", "N/A");
        }

        rank = mainController.getRank(user.getUsername());
        if (rank != null) {
            model.addAttribute("LoggedInUser_Rank", mainController.addRankSuffix(rank));
        } else {
            model.addAttribute("LoggedInUser_Rank", "N/A");
        }

        model.addAttribute("Points", friend.getPoints());
        model.addAttribute("LoggedInUser_Points", user.getPoints());
        model.addAttribute("LoggedInUser_League", user.getLeague());
        List<UserAchievement> achievements = friend.getUserAchievements().stream()
                .sorted(Comparator.comparing(UserAchievement::getDateAchieved))
                .collect(Collectors.toList());
        model.addAttribute("Achieved", achievements);
        model.addAttribute("notachieved", achievementRepository.findAll());

        return "Friends/friend-profile";
    }
}