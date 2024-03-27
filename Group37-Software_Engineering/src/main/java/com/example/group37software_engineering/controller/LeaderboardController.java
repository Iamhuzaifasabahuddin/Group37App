package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.League;
import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.model.Notification;
import com.example.group37software_engineering.repo.LeagueRepository;
import com.example.group37software_engineering.repo.NotificationRepository;
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
import java.util.Objects;
import java.util.Optional;
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

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private NotificationRepository notificationRepository;


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
        assignLeaguesToUsers();
        List<MyUser> bronze = userRepository.findAllByLeagueId(1);
        List<MyUser> silver = userRepository.findAllByLeagueId(2);
        List<MyUser> gold = userRepository.findAllByLeagueId(3);
        List<MyUser> platinum = userRepository.findAllByLeagueId(4);
        List<MyUser> titanium = userRepository.findAllByLeagueId(5);
        MyUser CurrentUser = userRepository.findByUsername(principal.getName());
        List<MyUser> Elysium = userRepository.findAllByLeagueId(7);
        List<MyUser> friends = CurrentUser.getFriends();
        friends.add(CurrentUser);
        List<MyUser> global = (List<MyUser>) userRepository.findAll();
        model.addAttribute("suffix", new String[]{"st", "nd", "rd", "th", "th", "th", "th", "th", "th", "th"});
        model.addAttribute("userLeague", CurrentUser.getLeague().getTitle());
        model.addAttribute("user", CurrentUser);


        model.addAttribute("global", global.stream()
                .filter(user -> user.getPoints() > 0)
                .sorted(Comparator.comparingDouble(MyUser::getPoints).reversed())
                .limit(10)
                .collect(Collectors.toList()));
        model.addAttribute("bronze", bronze.stream()
                .filter(user -> user.getPoints() > 0)
                .sorted(Comparator.comparingDouble(MyUser::getPoints).reversed())
                .limit(10)
                .collect(Collectors.toList()));
        model.addAttribute("silver", silver.stream()
                .filter(user -> user.getPoints() > 0)
                .sorted(Comparator.comparingDouble(MyUser::getPoints).reversed())
                .limit(10)
                .collect(Collectors.toList()));
        model.addAttribute("gold", gold.stream()
                .filter(user -> user.getPoints() > 0)
                .sorted(Comparator.comparingDouble(MyUser::getPoints).reversed())
                .limit(10)
                .collect(Collectors.toList()));
        model.addAttribute("platinum", platinum.stream()
                .filter(user -> user.getPoints() > 0)
                .sorted(Comparator.comparingDouble(MyUser::getPoints).reversed())
                .limit(10)
                .collect(Collectors.toList()));
        model.addAttribute("titanium", titanium.stream()
                .filter(user -> user.getPoints() > 0)
                .sorted(Comparator.comparingDouble(MyUser::getPoints).reversed())
                .limit(10)
                .collect(Collectors.toList()));
        model.addAttribute("elysium", Elysium.stream()
                .filter(user -> user.getPoints() > 0)
                .sorted(Comparator.comparingDouble(MyUser::getPoints).reversed())
                .limit(10)
                .collect(Collectors.toList()));
        model.addAttribute("friends", friends.stream()
                .filter(user -> user.getPoints() > 0)
                .sorted(Comparator.comparingDouble(MyUser::getPoints).reversed())
                .limit(10)
                .collect(Collectors.toList()));
        return "leaderboard";
    }

    /**
     * This method assigns leagues to all users based on their points.
     * It retrieves all users from the repository, checks their points, and assigns them to a league.
     * The leagues and points thresholds used here are just examples, you would need to adjust them to match your application's requirements.
     * After assigning the league, it saves the user back to the repository with their new league.
     */
    public void assignLeaguesToUsers() {
        List<MyUser> allUsers = (List<MyUser>) userRepository.findAll();

        for (MyUser user : allUsers) {
            int points = user.getPoints();
            if (points >= 16000) {
                user.setLeague(leagueRepository.findLeagueByTitle("Elysium"));
            } else if (points >= 12000) {
                user.setLeague(leagueRepository.findLeagueByTitle("Titanium Titan"));
            } else if (points >= 8000) {
                user.setLeague(leagueRepository.findLeagueByTitle("Platinum Prodigy"));
            } else if (points >= 4000) {
                user.setLeague(leagueRepository.findLeagueByTitle("Gold Guru"));
            } else if (points >= 2500) {
                user.setLeague(leagueRepository.findLeagueByTitle("Silver Sage"));
            } else if (points >= 1000) {
                user.setLeague(leagueRepository.findLeagueByTitle("Bronze Scholar"));
            }
            userRepository.save(user);
        }
    }

    /**
     * This method updates the leagues of a specific user based on their points.
     * It first sets the user's points to the previous points and saves the user.
     * Then it retrieves the previous leaderboard and global list of users, and calculates the user's previous rank.
     * It then sets the user's points to the current points and saves the user.
     * It checks if the user's points meet the threshold for each league, and if so, assigns the user to that league and sends a notification.
     * It then retrieves the current leaderboard and global list of users, and calculates the user's current rank.
     * It sends leaderboard notifications based on the user's previous and current ranks.
     * Finally, it saves the user.
     *
     * @param user The user to update the league for.
     * @param previousPoints The user's previous points.
     * @param currentPoints The user's current points.
     */
    public void updateleagues(MyUser user, int previousPoints, int currentPoints){
        user.setPoints(previousPoints);
        userRepository.save(user);
        List<MyUser> previousLeaderboard = userRepository.findAllByLeagueId(user.getLeague().getId()).stream().sorted(Comparator.comparingDouble(MyUser::getPoints).reversed()).toList();
        List<MyUser> previousGlobal = (List<MyUser>) userRepository.findAll();
        previousGlobal = previousGlobal.stream().sorted(Comparator.comparingDouble(MyUser::getPoints).reversed()).toList();
        int previousRank = previousGlobal.indexOf(user);
        boolean promoted = false;
        List<League> leagues = (List<League>) leagueRepository.findAll();
        leagues.sort(Comparator.comparing(League::getThreshold).reversed());
        user.setPoints(currentPoints);
        userRepository.save(user);
        for(League league : leagues) {
            if(league.getThreshold() <= user.getPoints()) {
                if (user.getLeague() == league) {
                    break;
                }
                promoted = true;
                user.setLeague(league);
                Notification n = new Notification();
                n.setDescription("You reached the " + league.getTitle() + " league!");
                n.setPageLink("/leaderboard");
                n.setIconLink(league.getImageUrl());
                notificationRepository.save(n);
                user.getNotifications().add(n);
                break;
            }
        }
        List<MyUser> currentLeaderboard = userRepository.findAllByLeagueId(user.getLeague().getId()).stream().sorted(Comparator.comparingDouble(MyUser::getPoints).reversed()).toList();
        List<MyUser> currentGlobal = (List<MyUser>) userRepository.findAll();
        currentGlobal = currentGlobal.stream().sorted(Comparator.comparingDouble(MyUser::getPoints).reversed()).toList();
        int currentRank = currentGlobal.indexOf(user);
        sendLeaderboardNotifications(previousGlobal, currentGlobal, user, previousRank > currentRank, true);
        sendLeaderboardNotifications(previousLeaderboard, currentLeaderboard, user, promoted, false);
        userRepository.save(user);

    }

    /**
     * This method sends leaderboard notifications to users.
     * It calculates the user's previous and current ranks in the leaderboard.
     * If the user is not unranked or the leaderboard is global, and the user's current rank is in the top 3, and the user's current rank is higher than their previous rank or they were promoted, it sends a notification to the user.
     * If there is a user who was dropped from their rank due to the user's promotion, it sends a notification to that user.
     *
     * @param previousLeaderboard The previous leaderboard.
     * @param currentLeaderboard The current leaderboard.
     * @param user The user to send notifications to.
     * @param promoted Whether the user was promoted.
     * @param isGlobal Whether the leaderboard is global.
     */
    public void sendLeaderboardNotifications(List<MyUser> previousLeaderboard, List<MyUser> currentLeaderboard, MyUser user, boolean promoted, boolean isGlobal) {
        int previousRank = previousLeaderboard.indexOf(user);
        int currentRank = currentLeaderboard.indexOf(user);
        if ((!Objects.equals(user.getLeague().getTitle(), "Unranked") || isGlobal) && currentRank < 3 && (currentRank < previousRank || promoted)) {
            Notification n1 = new Notification();
            if (isGlobal) {
                n1.setDescription("You reach #" + (currentRank + 1) + " in the global leaderboard!");
            } else {
                n1.setDescription("You reach #" + (currentRank + 1) + " in the " + user.getLeague().getTitle() + " league!");
            }
            n1.setPageLink("/leaderboard");
            n1.setIconLink(user.getLeague().getImageUrl());
            notificationRepository.save(n1);
            user.getNotifications().add(n1);
            if (currentLeaderboard.size() > currentRank + 1) {
                Optional<MyUser> droppedUser = userRepository.findById(currentLeaderboard.get(currentRank + 1).getId());
                if (droppedUser.isPresent()) {
                    Notification n2 = new Notification();
                    if (isGlobal) {
                        n2.setDescription(user.getUsername() + " took your #" + (currentRank + 1) + " place in the global leaderboard!");
                    } else {
                        n2.setDescription(user.getUsername() + " took your #" + (currentRank + 1) + " place in the " + user.getLeague().getTitle() + " league!");
                    }
                    n2.setPageLink("/leaderboard");
                    n2.setIconLink(user.getLeague().getImageUrl());
                    notificationRepository.save(n2);
                    droppedUser.get().getNotifications().add(n2);
                    userRepository.save(droppedUser.get());
                }
            }
        }
    }

}
