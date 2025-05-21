package com.alumverse.alumverse.repository;

import com.alumverse.alumverse.entity.Alumni;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumniRepository extends JpaRepository<Alumni, Long> {
}
