package com.alumverse.alumverse.repository;

import com.alumverse.alumverse.model.Alumni; // Make sure this package name matches where you put Alumni.java
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Alumni documents.
 * This provides CRUD (Create, Read, Update, Delete) operations for the 'alumni' collection.
 */
@Repository
public interface AlumniRepository extends MongoRepository<Alumni, String> {

    /**
     * Finds an alumni by their email address.
     * Spring Data MongoDB automatically implements this method based on its name.
     *
     * @param email The email to search for.
     * @return An Optional containing the found Alumni, or an empty Optional if not found.
     */
    Optional<Alumni> findByEmail(String email);

    List<Alumni> findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String query, String query1);
}
