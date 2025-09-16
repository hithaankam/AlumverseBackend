package com.alumverse.alumverse.config;

import com.alumverse.alumverse.model.Alumni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private final String id;
    private final String email;
    private final String password;
    private final String fullName;


    public CustomUserDetails(Alumni alumni) {
        this.id = alumni.getId();
        this.email = alumni.getEmail();
        this.password = alumni.getPassword();
        this.fullName = alumni.getFullName();
    }

    public String getId() { return id; }
    public String getFullName() { return fullName; }

    @Override
    public String getUsername() { return email; }

    @Override
    public String getPassword() { return password; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null; // Or roles if you implement them
    }

    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
}
