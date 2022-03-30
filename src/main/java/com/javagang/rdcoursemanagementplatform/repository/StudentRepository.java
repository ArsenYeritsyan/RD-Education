package com.javagang.rdcoursemanagementplatform.repository;

import com.javagang.rdcoursemanagementplatform.model.entity.Student;
import com.javagang.rdcoursemanagementplatform.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    Optional<Student> findByMail(String email);
}
