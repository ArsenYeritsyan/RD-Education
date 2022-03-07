package com.javagang.rdcoursemanagementplatform.repository;

import com.javagang.rdcoursemanagementplatform.model.entity.Homework;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HomeworkRepository extends JpaRepository<Homework, UUID> {
}