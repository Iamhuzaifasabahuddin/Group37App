package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.repo.LeagueRepository;
import com.example.group37software_engineering.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Pageable;
import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
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
     * The LeaderboardController class handles requests related to displaying the leaderboard.
     * It retrieves user data from the UserRepository, sorts them based on points, and sends the top 10 users
     * from each league to the view for display.
     * @param model the model to be sent to the view
     * @param principal the current user
     * @return the leaderboard view
     */

    @GetMapping("/leaderboard")
    public String getLeaderboard(Model model, Principal principal) {
        List<MyUser> bronze = userRepository.findAllByLeagueId(1);
        List<MyUser> silver = userRepository.findAllByLeagueId(2);
        List<MyUser> gold = userRepository.findAllByLeagueId(3);
        List<MyUser> platinum = userRepository.findAllByLeagueId(4);
        List<MyUser> titanium = userRepository.findAllByLeagueId(5);
        MyUser user = userRepository.findByUsername(principal.getName());
        List<MyUser> Elysium = userRepository.findAllByLeagueId(7);
        List<MyUser> friends = user.getFriends();
        List<MyUser> global = (List<MyUser>) userRepository.findAll();
        model.addAttribute("user", user);
        model.addAttribute("global", global.stream().sorted(Comparator.comparingDouble(MyUser::getPoints).reversed()).limit(10).collect(Collectors.toList()));
        model.addAttribute("userLeague", user.getLeague().getTitle());
        model.addAttribute("suffix", new String[]{"st", "nd", "rd", "th", "th", "th", "th", "th", "th", "th"});
        model.addAttribute("bronze", bronze.stream().sorted(Comparator.comparingDouble(MyUser::getPoints).reversed()).limit(10).collect(Collectors.toList()));
        model.addAttribute("silver", silver.stream().sorted(Comparator.comparingDouble(MyUser::getPoints).reversed()).limit(10).collect(Collectors.toList()));
        model.addAttribute("gold", gold.stream().sorted(Comparator.comparingDouble(MyUser::getPoints).reversed()).limit(10).collect(Collectors.toList()));
        model.addAttribute("platinum", platinum.stream().sorted(Comparator.comparingDouble(MyUser::getPoints).reversed()).limit(10).collect(Collectors.toList()));
        model.addAttribute("titanium", titanium.stream().sorted(Comparator.comparingDouble(MyUser::getPoints).reversed()).limit(10).collect(Collectors.toList()));
        model.addAttribute("elysium", Elysium.stream().sorted(Comparator.comparingDouble(MyUser::getPoints).reversed()).limit(10).collect(Collectors.toList()));
        model.addAttribute("friends", friends);
        return "leaderboard";
    }

}
