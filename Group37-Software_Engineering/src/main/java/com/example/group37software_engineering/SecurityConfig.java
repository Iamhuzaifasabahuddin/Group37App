package com.example.group37software_engineering;

import com.example.group37software_engineering.model.MyUser;
import com.example.group37software_engineering.service.RefreshingRememberMeServices;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.io.IOException;

/**
 * Configuration class for Spring Security settings.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Create a builder for MvcRequestMatcher.
     *
     * @param introspector The handler mapping introspector.
     * @return The MvcRequestMatcher builder.
     */
    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }

    /**
     * Configure the security settings for HTTP requests.
     *
     * @param http The HttpSecurity object to configure.
     * @param mvc  The MvcRequestMatcher builder.
     * @return The configured SecurityFilterChain.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        http.authorizeHttpRequests(auth ->
                auth.requestMatchers(mvc.pattern("/admin")).hasRole("ADMIN")
                        .requestMatchers(mvc.pattern("/dashboard")).hasAnyRole("NORMAL", "ADMIN")
                        .requestMatchers(mvc.pattern("/courses")).hasAnyRole("NORMAL", "ADMIN")
                        .requestMatchers(mvc.pattern("/profile")).hasAnyRole("NORMAL", "ADMIN")
                        .requestMatchers(mvc.pattern("/filter")).hasAnyRole("NORMAL", "ADMIN")
                        .requestMatchers(mvc.pattern("/search")).hasAnyRole("NORMAL", "ADMIN")
                        .requestMatchers(mvc.pattern("/duration")).hasAnyRole("NORMAL", "ADMIN")
                        .requestMatchers(mvc.pattern("/quiz")).hasAnyRole("NORMAL", "ADMIN")
                        .requestMatchers(mvc.pattern("/leaderboard")).hasAnyRole("NORMAL", "ADMIN")
                        .requestMatchers(mvc.pattern("/startTime")).hasAnyRole("NORMAL", "ADMIN")
                        .requestMatchers(mvc.pattern("/enroll")).hasAnyRole("NORMAL", "ADMIN")
                        .requestMatchers(mvc.pattern("/completeQuiz")).hasAnyRole("NORMAL", "ADMIN")
                        .requestMatchers(mvc.pattern("/top3")).hasAnyRole("NORMAL", "ADMIN")
                        .requestMatchers(mvc.pattern("/friends")).hasAnyRole("NORMAL", "ADMIN")
                        .requestMatchers(mvc.pattern("/getFriends")).hasAnyRole("NORMAL", "ADMIN")
                        .requestMatchers(mvc.pattern("/friend-profile")).hasAnyRole("NORMAL", "ADMIN")
                        .requestMatchers(mvc.pattern("/removeFriend")).hasAnyRole("NORMAL", "ADMIN")
                        .requestMatchers(mvc.pattern("/handleRequest")).hasAnyRole("NORMAL", "ADMIN")
                        .requestMatchers(mvc.pattern("/sendRequest")).hasAnyRole("NORMAL", "ADMIN")
                        .requestMatchers(mvc.pattern("/SearchFriends")).hasAnyRole("NORMAL", "ADMIN")
                        .requestMatchers(mvc.pattern("/achievements")).hasAnyRole("NORMAL", "ADMIN")
                        .requestMatchers(mvc.pattern("/comments")).hasAnyRole("NORMAL", "ADMIN")
                        .requestMatchers(mvc.pattern("/addComment")).hasAnyRole("NORMAL", "ADMIN")
                        .requestMatchers(mvc.pattern("/request")).hasAnyRole("NORMAL", "ADMIN")
                        .requestMatchers(mvc.pattern("/notifications")).hasAnyRole("NORMAL", "ADMIN")
                        .requestMatchers(mvc.pattern("/markAsRead")).hasAnyRole("NORMAL", "ADMIN")
                        .requestMatchers(mvc.pattern("/NewUser")).permitAll()
                        .requestMatchers(mvc.pattern("/AddUser")).permitAll()
                        .requestMatchers(mvc.pattern("/reset-email")).permitAll()
                        .requestMatchers(mvc.pattern("/reset-password")).permitAll()
                        .requestMatchers(mvc.pattern("/resetPasswordForm")).permitAll()
                        .requestMatchers(mvc.pattern("/reset")).permitAll()
                        .requestMatchers(mvc.pattern("/request")).permitAll()
                        .requestMatchers(mvc.pattern("/verifyEmail")).permitAll()
                        .requestMatchers(mvc.pattern("/welcome")).permitAll()
                        .requestMatchers(mvc.pattern(("/**"))).permitAll()
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .anyRequest().authenticated()
        ).formLogin(login -> login.loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("username-email")
                .passwordParameter("password")
                .defaultSuccessUrl("/success-login", true)
                .failureUrl("/error-login")
                .permitAll()
        ).logout(logout -> logout.deleteCookies("JSESSIONID", "remember-me")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/welcome")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .permitAll()
        ).exceptionHandling(exceptionHandler ->
                exceptionHandler.accessDeniedPage("/access-denied")
        ).rememberMe(
                rememberMe -> rememberMe.rememberMeServices(rememberMeServices(userDetailsService))
        );
        return http.build();
    }

    /**
     * Create an AuthenticationManager bean.
     *
     * @param authConfig The AuthenticationConfiguration object.
     * @return The AuthenticationManager bean.
     * @throws Exception If an error occurs while creating the bean.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * Create a PasswordEncoder bean using BCrypt.
     *
     * @return The PasswordEncoder bean.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RememberMeServices rememberMeServices(UserDetailsService userDetailsService) {
        RefreshingRememberMeServices rememberMeServices =
                new RefreshingRememberMeServices("unique", userDetailsService);
        rememberMeServices.setCookieName("remember-me");
        rememberMeServices.setTokenValiditySeconds(60 * 60 * 24 * 7);
        return rememberMeServices;
    }
}
