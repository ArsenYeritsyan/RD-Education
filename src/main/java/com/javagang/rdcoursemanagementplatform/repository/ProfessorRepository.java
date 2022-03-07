package com.javagang.rdcoursemanagementplatform.repository;

import com.javagang.rdcoursemanagementplatform.model.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfessorRepository extends JpaRepository<Professor, UUID> {
}