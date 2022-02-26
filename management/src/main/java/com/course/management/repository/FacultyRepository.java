package com.course.management.repository;

import com.course.management.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FacultyRepository extends JpaRepository<Faculty, UUID> {

    Faculty save(Faculty entity);

    List<Faculty> findAll();

    Optional<Faculty> findById(Faculty entityId);

//    Faculty update(Faculty entity);

    Faculty updateById(Faculty entity, UUID entityId);

    void delete(Faculty entity);

    void deleteById(UUID entityId);
}
