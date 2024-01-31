package com.example.group37software_engineering.service;

import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for loading user details for authentication.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // List of usernames considered as admin users
    private final List<String> adminUsernames = List.of("hs540", "amjo1", "atkm1", "st546", "gc272", "as1473", "ka381");

    /**
     * Load user details by username for authentication.
     *
     * @param username The username of the user to load.
     * @return UserDetails object representing the user.
     * @throws UsernameNotFoundException If the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Create a list of granted authorities based on user roles
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (isAdmin(user.getUsername())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_NORMAL"));
        }

        // Create UserDetails object with the user's details
        return new User(user.getUsername(), user.getPassword(), true, true, true, true, authorities);
    }

    /**
     * Check if a username is considered as an admin user.
     *
     * @param username The username to check.
     * @return True if the username is an admin user, false otherwise.
     */
    private boolean isAdmin(String username) {
        return adminUsernames.contains(username.toLowerCase());
    }
}
