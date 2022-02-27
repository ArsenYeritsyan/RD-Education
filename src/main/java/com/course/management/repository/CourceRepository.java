package com.course.management.repository;

import com.course.management.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourceRepository extends JpaRepository<Course, UUID>  {
}
