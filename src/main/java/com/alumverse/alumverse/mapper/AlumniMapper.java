package com.alumverse.alumverse.mapper;

import com.alumverse.alumverse.dto.AlumniDto;
import com.alumverse.alumverse.entity.Alumni;

public class AlumniMapper {
    public static AlumniDto mapToAlumniDto(Alumni alumni){
        return new AlumniDto(
                alumni.getId(),
          alumni.getFirstName(),
          alumni.getLastName(),
          alumni.getEmail()


        );
    }

    public static Alumni mapToAlumni(AlumniDto alumniDto){
        return new Alumni(
                alumniDto.getId(),
                alumniDto.getFirstName(),
                alumniDto.getLastName(),
                alumniDto.getEmail()
        );
    }
}
