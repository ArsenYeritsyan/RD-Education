package com.course.management.repository;

import com.course.management.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, UUID> {

    Professor save(Professor entity);

    List<Professor> findAll();

    Optional<Professor> findById(Professor entityId);

//    Professor update(Professor entity);

//    Professor updateById(Professor entity, UUID entityId);

    void delete(Professor entity);

    void deleteById(UUID entityId);

    List<Professor> findByFaculty_id(UUID departmentId);
}
