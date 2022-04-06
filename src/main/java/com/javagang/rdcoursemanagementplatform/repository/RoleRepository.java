package com.javagang.rdcoursemanagementplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.javagang.rdcoursemanagementplatform.model.entity.Role;
import com.javagang.rdcoursemanagementplatform.model.enums.RoleType;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
  Optional<Role> findByRoleType(RoleType roleType);
}
