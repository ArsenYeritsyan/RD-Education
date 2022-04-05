package com.javagang.rdcoursemanagementplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.javagang.rdcoursemanagementplatform.model.entity.User;

import java.util.UUID;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, UUID> {
  Optional<User> findByMail(String mail);
  Boolean existsUserByMail(String mail);
}
