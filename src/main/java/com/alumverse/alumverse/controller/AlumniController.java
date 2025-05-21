package com.alumverse.alumverse.controller;

import com.alumverse.alumverse.dto.AlumniDto;
import com.alumverse.alumverse.service.AlumniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alumni")
public class AlumniController {
    private AlumniService alumniService;
    @Autowired
    public AlumniController(AlumniService alumniService) {
        this.alumniService = alumniService;
    }
    //Add Employee REST API
    @PostMapping
    public ResponseEntity<AlumniDto> createAlumni(@RequestBody AlumniDto alumniDto){
        AlumniDto savedAlumni = alumniService.createAlumni(alumniDto);
        return new ResponseEntity<>(savedAlumni, HttpStatus.CREATED);
    }

    //Get Employee REST API
    @GetMapping("{id}")
    public ResponseEntity<AlumniDto> getEmployeeById(@PathVariable("id") Long id){
        AlumniDto alumniDto = alumniService.getAlumniById(id);
        return ResponseEntity.ok(alumniDto);
    }
    //Get All REST API
    @GetMapping
    public ResponseEntity<List<AlumniDto>> getAllAlumni(){
        List<AlumniDto> allAlumni= alumniService.getAllAlumni();
        return ResponseEntity.ok(allAlumni);
    }
    @PutMapping("{id}")
    public ResponseEntity<AlumniDto> updateAlumni(@RequestBody AlumniDto alumniDto, @PathVariable("id") Long id){
        AlumniDto updatedAlumniDto = alumniService.updateAlumni(id, alumniDto);
        return ResponseEntity.ok(updatedAlumniDto);

    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAlumni(@PathVariable("id") Long id){
        alumniService.deleteAlumni(id);
        return ResponseEntity.ok("Deleted Alumni with id " + id);
    }

}
