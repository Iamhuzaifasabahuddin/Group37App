package com.example.group37software_engineering.controller;

import com.example.group37software_engineering.model.Notification;
import com.example.group37software_engineering.repo.NotificationRepository;
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
import java.util.Optional;

@RestController
public class AjaxNotificationController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    /**
     * Handles the "/notifications" endpoint to send list of notifications for the current user.
     *
     * @param principal        The principal representing the currently authenticated user.
     * @return ResponseEntity JSON object containing the list of notifications.
     */
    @RequestMapping("/notifications")
    public ResponseEntity<?> getNotifications(Principal principal) {
        String username = principal.getName();
        List<Notification> notificationList = userRepository.findByUsername(username).getNotifications();
        Gson gson = new Gson();
        return ResponseEntity.ok().body("{\"notifications\":" + gson.toJson(notificationList) + "}");
    }

    /**
     * Handles the "/markAsRead" endpoint to mark one or all notifications as read.
     *
     * @param principal        The principal representing the currently authenticated user.
     * @param notificationId   Notification to be marked as read.
     * @return ResponseEntity indicating success of request.
     */
    @RequestMapping("/markAsRead")
    public ResponseEntity<?> markAsRead(Principal principal, @RequestParam(required = false) Integer notificationId) {
        String username = principal.getName();
        if (notificationId != null) {
            Optional<Notification> n = notificationRepository.findById(notificationId);
            if (n.isPresent()) {
                n.get().setSeen(true);
                notificationRepository.save(n.get());
            }
        } else {
            List<Notification> notificationList = userRepository.findByUsername(username).getNotifications();
            for (Notification notification: notificationList) {
                notification.setSeen(true);
                notificationRepository.save(notification);
            }
        }
        return ResponseEntity.ok().body("");
    }
}
