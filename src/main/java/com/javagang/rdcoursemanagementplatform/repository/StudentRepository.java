package com.javagang.rdcoursemanagementplatform.repository;

import org.springframework.stereotype.Repository;
import com.javagang.rdcoursemanagementplatform.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {}
