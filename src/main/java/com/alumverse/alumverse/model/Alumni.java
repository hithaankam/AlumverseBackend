package com.alumverse.alumverse.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.index.Indexed;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Represents an Alumni document in the MongoDB database.
 * This class maps to the 'alumni' collection.
 */
@Document(collection = "alumni")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alumni {

    @Id
    private String id; // MongoDB uses String for its _id field

    @Field("full_name")
    private String fullName;

    @Field("email")
    @Indexed(unique = true) // This ensures every email is unique in the database
    private String email;

    @Field("password")
    private String password; // This will store the hashed password

}
