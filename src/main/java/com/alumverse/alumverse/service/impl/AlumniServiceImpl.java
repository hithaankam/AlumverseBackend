package com.alumverse.alumverse.service.impl;

import com.alumverse.alumverse.dto.AlumniDto;
import com.alumverse.alumverse.model.Alumni;
import com.alumverse.alumverse.exception.ResourceNotFoundException;
import com.alumverse.alumverse.mapper.AlumniMapper;
import com.alumverse.alumverse.repository.AlumniRepository;
import com.alumverse.alumverse.service.AlumniService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AlumniServiceImpl implements AlumniService {

    private final AlumniRepository alumniRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AlumniDto createAlumni(AlumniDto alumniDto) {
        // Check if user already exists to prevent duplicates
        alumniRepository.findByEmail(alumniDto.getEmail()).ifPresent(a -> {
            throw new RuntimeException("User with email " + alumniDto.getEmail() + " already exists.");
        });

        Alumni alumni = AlumniMapper.mapToAlumni(alumniDto);

        // Hash the password before saving the new alumni
        alumni.setPassword(passwordEncoder.encode(alumni.getPassword()));

        Alumni savedAlumni = alumniRepository.save(alumni);

        return AlumniMapper.mapToAlumniDto(savedAlumni);
    }

    @Override
    public AlumniDto getAlumniById(String alumniId) { // CORRECT: Uses String
        Alumni alumni = alumniRepository.findById(alumniId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Alumni Not Found with Id: " + alumniId));
        return AlumniMapper.mapToAlumniDto(alumni);
    }

    @Override
    public List<AlumniDto> getAllAlumni() {
        List<Alumni> allAlumni = alumniRepository.findAll();
        return allAlumni.stream()
                .map(AlumniMapper::mapToAlumniDto)
                .collect(Collectors.toList());
    }

    @Override
    public AlumniDto updateAlumni(String alumniId, AlumniDto alumniDto) { // CORRECT: Uses String
        Alumni alumni = alumniRepository.findById(alumniId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Alumni Not Found with Id: " + alumniId));

        alumni.setFullName(alumniDto.getFullName());
        alumni.setEmail(alumniDto.getEmail());

        // Only update password if a new one is provided in the DTO
        if (StringUtils.hasText(alumniDto.getPassword())) {
            alumni.setPassword(passwordEncoder.encode(alumniDto.getPassword()));
        }

        Alumni updatedAlumni = alumniRepository.save(alumni);
        return AlumniMapper.mapToAlumniDto(updatedAlumni);
    }

    @Override
    public void deleteAlumni(String alumniId) { // CORRECT: Uses String
        if (!alumniRepository.existsById(alumniId)) {
            throw new ResourceNotFoundException("Alumni Not Found with Id: " + alumniId);
        }
        alumniRepository.deleteById(alumniId);
    }

    @Override
    public List<AlumniDto> searchAlumni(String query) {
        // The repository method name is long, but it's descriptive
        List<Alumni> matchedAlumni = alumniRepository
                .findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(query, query);

        return matchedAlumni.stream()
                .map(AlumniMapper::mapToAlumniDto)
                .collect(Collectors.toList());
    }
}
