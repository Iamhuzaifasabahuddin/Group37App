package com.example.group37software_engineering.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Custom RememberMeServices that extends TokenBasedRememberMeServices to provide token refreshing functionality.
 */
public class RefreshingRememberMeServices extends TokenBasedRememberMeServices {

    /**
     * Constructs a new RefreshingRememberMeServices with the specified key and UserDetailsService.
     * @param key The key used for token generation.
     * @param userDetailsService The UserDetailsService for retrieving user details.
     */
    public RefreshingRememberMeServices(String key, UserDetailsService userDetailsService) {
        super(key, userDetailsService);
    }

    @Override
    protected UserDetails processAutoLoginCookie(String[] cookieTokens, HttpServletRequest request,
                                                 HttpServletResponse response) {

        UserDetails userDetails = super.processAutoLoginCookie(cookieTokens, request, response);

        setCookie(makeTokens(userDetails.getUsername(), request), getTokenValiditySeconds(), request, response);

        return userDetails;
    }

    /**
     * Generates new tokens for refreshing the remember-me cookie.
     * @param username The username associated with the tokens.
     * @param request The HttpServletRequest.
     * @return An array of tokens.
     */
    protected String[] makeTokens(String username, HttpServletRequest request) {
        long tokenExpiryTime = System.currentTimeMillis() + getTokenValiditySeconds() * 1000L;
        String salt = generateSalt();
        String signatureValue = makeTokenSignature(tokenExpiryTime, username, salt);
        return new String[] {username, Long.toString(tokenExpiryTime), signatureValue, salt};
    }

    /**
     * Generates the token signature based on the provided parameters.
     * @param tokenExpiryTime The expiry time of the token.
     * @param username The username associated with the token.
     * @param salt The salt used for hashing.
     * @return The token signature.
     */
    protected String makeTokenSignature(long tokenExpiryTime, String username, String salt) {
        String data = username + ":" + tokenExpiryTime + ":" + salt + ":" + getKey();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return new String(Hex.encode(digest.digest(data.getBytes())));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No SHA-256 algorithm available!");
        }
    }

    /**
     * Generates a random salt for token generation.
     * @return The generated salt.
     */
    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
}
