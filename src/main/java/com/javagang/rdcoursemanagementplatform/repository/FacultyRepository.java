package com.javagang.rdcoursemanagementplatform.repository;

import com.javagang.rdcoursemanagementplatform.model.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FacultyRepository extends JpaRepository<Faculty, UUID> {
}