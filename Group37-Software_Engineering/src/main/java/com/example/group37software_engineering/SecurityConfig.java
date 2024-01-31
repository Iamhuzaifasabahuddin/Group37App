package com.example.group37software_engineering;

import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

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
}
