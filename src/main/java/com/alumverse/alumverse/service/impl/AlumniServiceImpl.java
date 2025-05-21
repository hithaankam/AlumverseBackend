package com.alumverse.alumverse.service.impl;

import com.alumverse.alumverse.dto.AlumniDto;
import com.alumverse.alumverse.entity.Alumni;
import com.alumverse.alumverse.exception.ResourceNotFoundException;
import com.alumverse.alumverse.mapper.AlumniMapper;
import com.alumverse.alumverse.repository.AlumniRepository;
import com.alumverse.alumverse.service.AlumniService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AlumniServiceImpl implements AlumniService {
    private AlumniRepository alumniRepository;
    @Override
    public AlumniDto createAlumni(AlumniDto alumnidto) {
        Alumni alumni = AlumniMapper.mapToAlumni(alumnidto);
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
        List<Alumni> allAlumni= alumniRepository.findAll();
        return allAlumni.stream().map((alumni) -> AlumniMapper.mapToAlumniDto(alumni))
                .collect(Collectors.toList());
    }
    @Override
    public AlumniDto updateAlumni(Long alumniId, AlumniDto alumnidto) {
        Alumni alumni = alumniRepository.findById(alumniId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Alumni Not Found with Id: " + alumniId));
        alumni.setFirstName(alumnidto.getFirstName());
        alumni.setLastName(alumnidto.getLastName());
        alumni.setEmail(alumnidto.getEmail());
        Alumni updatedAlumni = alumniRepository.save(alumni);
        return AlumniMapper.mapToAlumniDto(updatedAlumni);
    }

    @Override
    public void deleteAlumni(Long alumniId) {
        Alumni alumni = alumniRepository.findById(alumniId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Alumni Not Found with Id: " + alumniId));
        alumniRepository.deleteById(alumniId);
    }


}
