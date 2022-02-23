package com.example.demologin.security.user;

import com.example.demologin.entity.User;
import com.example.demologin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user != null && user.isStatus()){
            return new CustomUserDetails(user);
        } else if (user == null){
            throw new UsernameNotFoundException("User get email " + email + " does not exist.");
        } else {
            throw new UsernameNotFoundException("User get email " + email + " isn't enable to login.");
        }
    }
}
