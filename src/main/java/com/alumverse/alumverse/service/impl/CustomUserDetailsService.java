package com.alumverse.alumverse.service.impl;

import com.alumverse.alumverse.config.CustomUserDetails;
import com.alumverse.alumverse.model.Alumni;
import com.alumverse.alumverse.repository.AlumniRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final AlumniRepository alumniRepository;

    public CustomUserDetailsService(AlumniRepository alumniRepository) {
        this.alumniRepository = alumniRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Alumni alumni = alumniRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new CustomUserDetails(alumni);
    }
}
