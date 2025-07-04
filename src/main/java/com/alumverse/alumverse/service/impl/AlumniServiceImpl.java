package com.alumverse.alumverse.service.impl;

import com.alumverse.alumverse.dto.AlumniDto;
import com.alumverse.alumverse.model.Alumni;
import com.alumverse.alumverse.exception.ResourceNotFoundException;
import com.alumverse.alumverse.mapper.AlumniMapper;
import com.alumverse.alumverse.repository.AlumniRepository;
import com.alumverse.alumverse.service.AlumniService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder; // Import PasswordEncoder
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils; // Import for checking string content

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AlumniServiceImpl implements AlumniService {

    private final AlumniRepository alumniRepository;
    private final PasswordEncoder passwordEncoder; // Inject PasswordEncoder for security

    @Override
    public AlumniDto createAlumni(AlumniDto alumniDto) {
        Alumni alumni = AlumniMapper.mapToAlumni(alumniDto);

        // Hash the password before saving the new alumni
        alumni.setPassword(passwordEncoder.encode(alumni.getPassword()));

        Alumni savedAlumni = alumniRepository.save(alumni);

        return AlumniMapper.mapToAlumniDto(savedAlumni);
    }

    @Override
    public AlumniDto getAlumniById(Long alumniId) {
        Alumni alumni = alumniRepository.findById(alumniId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Alumni Not Found with Id: " + alumniId));
        return AlumniMapper.mapToAlumniDto(alumni);
    }

    @Override
    public List<AlumniDto> getAllAlumni() {
        List<Alumni> allAlumni = alumniRepository.findAll();
        return allAlumni.stream()
                .map(AlumniMapper::mapToAlumniDto) // Using method reference for cleaner code
                .collect(Collectors.toList());
    }

    @Override
    public AlumniDto updateAlumni(Long alumniId, AlumniDto alumniDto) {
        Alumni alumni = alumniRepository.findById(alumniId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Alumni Not Found with Id: " + alumniId));

        // Update fields from the DTO
        alumni.setFullName(alumniDto.getFullName());
        alumni.setEmail(alumniDto.getEmail());

        // Only update the password if a new one is provided
        if (StringUtils.hasText(alumniDto.getPassword())) {
            alumni.setPassword(passwordEncoder.encode(alumniDto.getPassword()));
        }

        Alumni updatedAlumni = alumniRepository.save(alumni);
        return AlumniMapper.mapToAlumniDto(updatedAlumni);
    }

    @Override
    public void deleteAlumni(Long alumniId) {
        // First, ensure the alumni exists before attempting to delete
        if (!alumniRepository.existsById(alumniId)) {
            throw new ResourceNotFoundException("Alumni Not Found with Id: " + alumniId);
        }
        alumniRepository.deleteById(alumniId);
    }
}