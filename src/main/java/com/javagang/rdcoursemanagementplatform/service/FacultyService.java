package com.javagang.rdcoursemanagementplatform.service;

import com.javagang.rdcoursemanagementplatform.exception.CourseNotFoundException;
import com.javagang.rdcoursemanagementplatform.exception.FacultyNotFoundException;
import com.javagang.rdcoursemanagementplatform.model.dto.FacultyDTO;
import com.javagang.rdcoursemanagementplatform.model.entity.Course;
import com.javagang.rdcoursemanagementplatform.model.entity.Faculty;
import com.javagang.rdcoursemanagementplatform.model.mapper.MapStruct;
import com.javagang.rdcoursemanagementplatform.repository.CourseRepository;
import com.javagang.rdcoursemanagementplatform.repository.FacultyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FacultyService {
  private final FacultyRepository facultyRepository;
  private final CourseRepository courseRepository;
  private final MapStruct map;

  public FacultyDTO saveFaculty(FacultyDTO facultyDTO) {
    log.info("FacultyService::saveFaculty -> Faculty DTO passed  = {}", facultyDTO);
    Faculty faculty = facultyRepository.save(map.facultyDTOToFaculty(facultyDTO));
    return map.facultyToFacultyDTO(faculty);
  }

  public FacultyDTO findFacultyById(UUID id) {
    log.info("FacultyService::findFacultyById -> id passed = {}", id);
    return facultyRepository
        .findById(id)
        .map(map::facultyToFacultyDTO)
        .orElseThrow(() -> new FacultyNotFoundException(id.toString()));
  }

  public List<FacultyDTO> findAllFaculties() {
    log.info("FacultyService::findAllFaculties");
    return facultyRepository.findAll().stream()
        .map(map::facultyToFacultyDTO)
        .collect(Collectors.toList());
  }

  public FacultyDTO updateFaculty(UUID id, FacultyDTO facultyDTO) {
    log.info("FacultyService::updateFaculty -> faculty DTO passed  = {}", facultyDTO);
    var faculty =
        facultyRepository
            .findById(id)
            .orElseThrow(() -> new FacultyNotFoundException(id.toString()));
    faculty.setFacultyName(facultyDTO.getFacultyName());
    return saveFacultyToDatabase(faculty);
  }

  public void deleteFacultyById(UUID id) {
    log.info("FacultyService::deleteFacultyById -> id passed = {}", id);
    var faculty =
        facultyRepository
            .findById(id)
            .orElseThrow(() -> new FacultyNotFoundException(id.toString()));
    facultyRepository.deleteById(faculty.getId());
  }

  public FacultyDTO findFacultyByName(String name) {
    log.info("FacultyService::findAllFacultyByName -> course name passed = {}", name);
    Faculty faculty =
        facultyRepository
            .findByFacultyName(name)
            .orElseThrow(() -> new FacultyNotFoundException(name));
    return map.facultyToFacultyDTO(faculty);
  }

  public FacultyDTO addCourseToFaculty(UUID facultyId, UUID courseId) {
    log.info(
        "FacultyService::addCourseToFaculty -> faculty id  = {} -> course id passed = {}",
        facultyId,
        courseId);
    Course course =
        courseRepository
            .findById(courseId)
            .orElseThrow(() -> new CourseNotFoundException(courseId.toString()));
    Faculty faculty =
        facultyRepository
            .findById(facultyId)
            .orElseThrow(() -> new FacultyNotFoundException(facultyId.toString()));
    faculty.getCourses().add(course);
    return saveFacultyToDatabase(faculty);
  }

  private FacultyDTO saveFacultyToDatabase(Faculty faculty) {
    Faculty savedFaculty = facultyRepository.save(faculty);
    return map.facultyToFacultyDTO(savedFaculty);
  }
}
