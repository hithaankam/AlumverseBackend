package com.alumverse.alumverse.controller;

import com.alumverse.alumverse.config.CustomUserDetails;
import com.alumverse.alumverse.dto.AlumniDto;
import com.alumverse.alumverse.service.AlumniService;
import com.alumverse.alumverse.service.impl.AlumniServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*") // Allows requests from any frontend, useful for development
@RestController
@RequestMapping("/api/alumni")
public class AlumniController {

    private final AlumniService alumniService;

    @Autowired
    public AlumniController(AlumniService alumniService) {
        this.alumniService = alumniService;
    }

    // This endpoint is handled by the AuthController's /register endpoint now.
    // You can keep it for admin purposes or remove it to avoid confusion.
    // @PostMapping
    // public ResponseEntity<AlumniDto> createAlumni(@RequestBody AlumniDto alumniDto){
    //     AlumniDto savedAlumni = alumniService.createAlumni(alumniDto);
    //     return new ResponseEntity<>(savedAlumni, HttpStatus.CREATED);
    // }

    // Get a specific alumni's profile by their ID
    // This should be a protected endpoint in the future.
    @GetMapping("/{id}")
    public ResponseEntity<AlumniDto> getAlumniById(@PathVariable("id") String id){ // Changed to String
        AlumniDto alumniDto = alumniService.getAlumniById(id);
        return ResponseEntity.ok(alumniDto);
    }

    // Get all alumni profiles
    // This should also be protected and likely only available to admins.
    @GetMapping
    public ResponseEntity<List<AlumniDto>> getAllAlumni(){
        List<AlumniDto> allAlumni = alumniService.getAllAlumni();
        return ResponseEntity.ok(allAlumni);
    }

    // Update an alumni's profile
    // This should be protected so only the user themselves or an admin can do this.
    @PutMapping("/{id}")
    public ResponseEntity<AlumniDto> updateAlumni(@RequestBody AlumniDto alumniDto, @PathVariable("id") String id){ // Changed to String
        AlumniDto updatedAlumniDto = alumniService.updateAlumni(id, alumniDto);
        return ResponseEntity.ok(updatedAlumniDto);
    }

    // Delete an alumni's profile
    // This should be protected and only available to the user or an admin.
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAlumni(@PathVariable("id") String id){ // Changed to String
        alumniService.deleteAlumni(id);
        return ResponseEntity.ok("Deleted Alumni with id " + id);
    }

    // Search for alumni
    @GetMapping("/search")
    public ResponseEntity<List<AlumniDto>> searchAlumni(@RequestParam String name) {
        List<AlumniDto> currSearch = alumniService.searchAlumni(name);
        return ResponseEntity.ok(currSearch);
    }

    @GetMapping("/me")
    public ResponseEntity<AlumniDto> getMe(@AuthenticationPrincipal CustomUserDetails userDetails) {
        AlumniDto alumni = alumniService.getAlumniById(userDetails.getId());
        return ResponseEntity.ok(alumni);
    }


}

