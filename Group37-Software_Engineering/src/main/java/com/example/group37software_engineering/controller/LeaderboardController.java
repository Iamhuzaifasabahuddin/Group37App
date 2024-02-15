package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Comparator;
import java.util.List;
import java.util.stream.StreamSupport;

/**
 * The LeaderboardController class handles requests related to displaying the leaderboard.
 * It retrieves user data from the UserRepository, sorts them based on points, and sends the top 5 users
 * to the view for display.
 */

@Controller
public class LeaderboardController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves the top 5 users based on points from the UserRepository and adds them to the model.
     *
     * @param model The model to which the top users will be added for rendering in the view.
     * @return The name of the view to display the leaderboard.
     */
    @GetMapping("/leaderboard")
    public String getLeaderboard(Model model) {
        List<MyUser> myUsers = StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .sorted(Comparator.comparingDouble(MyUser::getPoints).reversed())
                .toList().subList(0, 5);
        model.addAttribute("Users", myUsers);
        return "leaderboard";
    }

}
