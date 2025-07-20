package com.alumverse.alumverse.service;

import com.alumverse.alumverse.dto.AlumniDto;

import java.util.List;

public interface AlumniService {
    AlumniDto createAlumni(AlumniDto alumnidto);
    AlumniDto getAlumniById(Long alumniId);
    List<AlumniDto> getAllAlumni();
    AlumniDto updateAlumni(Long alumniId, AlumniDto updatedAlumni);
    void deleteAlumni(Long alumniId);
    List<AlumniDto> searchAlumni(String name);
}
