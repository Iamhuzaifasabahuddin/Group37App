package com.example.group37software_engineering;

import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        http.authorizeHttpRequests(auth ->
                auth.requestMatchers(mvc.pattern("/admin")) .hasRole("ADMIN")
                        .requestMatchers(mvc.pattern("/dashboard")).hasAnyRole("NORMAL", "ADMIN")
                        .requestMatchers(mvc.pattern("/NewUser")).permitAll()
                        .requestMatchers(mvc.pattern("/AddUser")).permitAll()
                        .requestMatchers(mvc.pattern(("/**"))).permitAll()
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .anyRequest().authenticated()
        ).formLogin(login -> login.loginPage("/login-form")
                .loginProcessingUrl("/myLogin")
                .defaultSuccessUrl("/success-login", true)
                .failureUrl("/error-login")
                .permitAll()
        ).logout(logout -> logout.invalidateHttpSession(true)
                .logoutSuccessUrl("/login-form")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .permitAll()
        ).exceptionHandling(exceptionHandler ->
                exceptionHandler.accessDeniedPage("/access-denied")
        );
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
