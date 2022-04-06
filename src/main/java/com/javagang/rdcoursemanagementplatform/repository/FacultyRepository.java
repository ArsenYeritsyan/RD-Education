package com.javagang.rdcoursemanagementplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.javagang.rdcoursemanagementplatform.model.entity.Faculty;

import java.util.UUID;
import java.util.Optional;

public interface FacultyRepository extends JpaRepository<Faculty, UUID> {
    Optional<Faculty> findByFacultyName(String facultyName);
}
