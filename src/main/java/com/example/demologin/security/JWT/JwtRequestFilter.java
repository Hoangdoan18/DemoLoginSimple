package com.example.demologin.security.JWT;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    private static String HEADER = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
         // Get token from header
        String token;
        Cookie cookie = WebUtils.getCookie(request, "JWT_TOKEN");
        if (cookie != null) {
            token = cookie.getValue();
        } else {
            filterChain.doFilter(request, response);
            log.error("Cookie null.");
            return;
        }

        // Parse claims from token
        Claims claims = jwtTokenUtil.getClaimsFromToken(token);
        if (claims == null) {
            filterChain.doFilter(request,response);
            log.error("Invalid JWT token.");
            return;
        }

        // Check expired time of token
        Date expiration = claims.getExpiration();
        if (expiration.before(new Date())) {
            filterChain.doFilter(request,response);
            log.error("Expired JWT token.");
            return;
        }

        // Create Authentication
        UsernamePasswordAuthenticationToken authenticationObject = getAuthentication(claims);
        if (authenticationObject == null) {
            log.error("Authentication object null.");
            filterChain.doFilter(request,response);
            return;
        }

        // Authenticate success , save object to SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authenticationObject);
        filterChain.doFilter(request,response);
    }

    // Get information from token
    private UsernamePasswordAuthenticationToken getAuthentication(Claims claims) {
        String username = claims.getSubject();

        if (username != null) {
            UserDetails user = userDetailsService.loadUserByUsername(username);
            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        }
        return null;
    }
}
