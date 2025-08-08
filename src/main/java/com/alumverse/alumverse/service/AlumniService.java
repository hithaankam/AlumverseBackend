package com.alumverse.alumverse.service;

import com.alumverse.alumverse.dto.AlumniDto;

import java.util.List;

public interface AlumniService {
    AlumniDto createAlumni(AlumniDto alumnidto);
    AlumniDto getAlumniById(String alumniId);
    List<AlumniDto> getAllAlumni();
    AlumniDto updateAlumni(String alumniId, AlumniDto updatedAlumni);
    void deleteAlumni(String alumniId);
    List<AlumniDto> searchAlumni(String name);
}
