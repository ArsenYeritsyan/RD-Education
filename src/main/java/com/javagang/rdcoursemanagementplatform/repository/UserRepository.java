package com.javagang.rdcoursemanagementplatform.repository;

import com.javagang.rdcoursemanagementplatform.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}