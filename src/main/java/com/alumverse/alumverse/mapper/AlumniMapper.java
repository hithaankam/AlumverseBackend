package com.alumverse.alumverse.mapper;

import com.alumverse.alumverse.dto.AlumniDto;
import com.alumverse.alumverse.model.Alumni;
import org.bson.types.ObjectId;
public class AlumniMapper {

    /**
     * Maps an Alumni entity to an AlumniDto.
     * Note: For security, the password fields in the DTO are set to null.
     *
     * @param alumni the Alumni entity to map
     * @return the resulting AlumniDto
     */
    public static AlumniDto mapToAlumniDto(Alumni alumni){
        return new AlumniDto(

                alumni.getId(),
                alumni.getFullName(),
                alumni.getEmail(),
                null, // Do not expose password hash
                null  // Do not expose password hash
        );
    }

    public static Alumni mapToAlumni(AlumniDto alumniDto){
        return new Alumni(
                alumniDto.getId(),
                alumniDto.getFullName(),
                alumniDto.getEmail(),
                alumniDto.getPassword() // The service should handle password hashing
        );
    }
}