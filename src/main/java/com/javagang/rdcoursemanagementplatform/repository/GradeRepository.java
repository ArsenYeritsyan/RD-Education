package com.javagang.rdcoursemanagementplatform.repository;

import com.javagang.rdcoursemanagementplatform.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GradeRepository extends JpaRepository<Grade, UUID> {
}
