package com.alumverse.alumverse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlumniDto {
    private String id;
    private String fullName;
    private String email;
    private String password;
    private String confirmPassword;


    public String getId(){
        return this.id;
    }
    public String getFullName(){
        return this.fullName;
    }
}
