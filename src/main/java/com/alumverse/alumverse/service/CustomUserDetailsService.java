package com.alumverse.alumverse.service;

import com.alumverse.alumverse.model.Alumni;
import com.alumverse.alumverse.repository.AlumniRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * This service is responsible for loading user-specific data for Spring Security.
 * It connects our Alumni database model to Spring's security framework.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AlumniRepository alumniRepository;

    public CustomUserDetailsService(AlumniRepository alumniRepository) {
        this.alumniRepository = alumniRepository;
    }

    /**
     * This method is called by Spring Security during the authentication process.
     * It finds a user by their email (which we use as the username).
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Alumni alumni = alumniRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Converts our Alumni object into a UserDetails object that Spring Security understands.
        return new User(alumni.getEmail(), alumni.getPassword(), new ArrayList<>());
    }
}
