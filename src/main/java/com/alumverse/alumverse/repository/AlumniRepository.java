package com.alumverse.alumverse.repository;

import com.alumverse.alumverse.model.Alumni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AlumniRepository extends MongoRepository<Alumni, Long> {
    List<Alumni> findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String query, String query1);
}
