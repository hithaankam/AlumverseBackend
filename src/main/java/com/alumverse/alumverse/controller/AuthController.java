package com.alumverse.alumverse.controller;

import com.alumverse.alumverse.dto.AlumniDto;
import com.alumverse.alumverse.dto.AuthResponseDto;
import com.alumverse.alumverse.dto.LoginDto;
import com.alumverse.alumverse.dto.RegisterDto;
import com.alumverse.alumverse.config.JwtTokenProvider;
import com.alumverse.alumverse.service.AlumniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "https://alumverse.netlify.app")

public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AlumniService alumniService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    /**
     * Handles the user login request.
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new AuthResponseDto(token));
    }

    /**
     * Handles the user registration request.
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        AlumniDto alumniDto = new AlumniDto();
        alumniDto.setFullName(registerDto.getFullName());
        alumniDto.setEmail(registerDto.getEmail());
        alumniDto.setPassword(registerDto.getPassword());


        alumniService.createAlumni(alumniDto);

        return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
    }
}
