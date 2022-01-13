package com.example.demologin.security.JWT;


import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtTokenUtil {
    public static final String PREFIX = "Bearer";

    // Set expired time for token, count by number of seconds
    @Value("${jwt.duration}")
    public Integer duration;


    @Value("${jwt.secret}")
    public String secret;

    // Generate token
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        // 1. Define all claims: issuer, expiration, subject, id
        // 2. Encoding token with HS512 algorithm and secret key
        // 3. Convert to a safe URL String
        // 4. Append new String after PREFIX "Bearer "
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + duration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

        return PREFIX + token;
    }

    // Get information from token
    public Claims getClaimsFromToken(String token){
        // Check token
        if (token == null || !token.startsWith(PREFIX)) return null;
        try {
            token = token.replace(PREFIX, "");
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return null;
    }
}
