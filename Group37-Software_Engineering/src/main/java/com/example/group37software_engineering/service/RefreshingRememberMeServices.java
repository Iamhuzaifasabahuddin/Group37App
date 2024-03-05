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

public class RefreshingRememberMeServices extends TokenBasedRememberMeServices {

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

    protected String[] makeTokens(String username, HttpServletRequest request) {
        long tokenExpiryTime = System.currentTimeMillis() + getTokenValiditySeconds() * 1000L;
        String salt = generateSalt();
        String signatureValue = makeTokenSignature(tokenExpiryTime, username, salt);
        return new String[] {username, Long.toString(tokenExpiryTime), signatureValue, salt};
    }

    protected String makeTokenSignature(long tokenExpiryTime, String username, String salt) {
        String data = username + ":" + tokenExpiryTime + ":" + salt + ":" + getKey();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return new String(Hex.encode(digest.digest(data.getBytes())));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No SHA-256 algorithm available!");
        }
    }
    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
}