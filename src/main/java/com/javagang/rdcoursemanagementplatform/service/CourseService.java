package com.javagang.rdcoursemanagementplatform.service;

import com.javagang.rdcoursemanagementplatform.exception.CourseNotFoundException;
import com.javagang.rdcoursemanagementplatform.model.dto.CourseDTO;
import com.javagang.rdcoursemanagementplatform.model.entity.Course;
import com.javagang.rdcoursemanagementplatform.model.mapper.MapStruct;
import com.javagang.rdcoursemanagementplatform.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseService {
  private final CourseRepository courseRepository;
  private final MapStruct map;

  public CourseDTO saveCourse(CourseDTO courseDTO) {
    log.info("CourseService::saveCourse -> course DTO passed  = {}", courseDTO);
//    validateCourseExistence(courseDTO);
  return saveCourseToDatabase(map.courseDTOToCourse(courseDTO));
  }

  public List<CourseDTO> findAllCourses() {
    log.info("CourseService::findAllCourses");

    return courseRepository.findAll().stream()
        .map(map::courseToCourseDTO)
        .collect(Collectors.toList());
  }

    public CourseDTO findCourseById(UUID id) {
    log.info("CourseService::findCourseById -> id passed = {}", id);
    return courseRepository
        .findById(id)
        .map(map::courseToCourseDTO)
        .orElseThrow(() -> new CourseNotFoundException(id.toString()));
  }

  public CourseDTO updateCourse(UUID id, CourseDTO courseDTO) {
    log.info("CourseService::updateCourse -> course DTO passed  = {}", courseDTO);
    Course course =
        courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException(id.toString()));
    var course1 = map.courseDTOToCourse(courseDTO);
    return saveCourseToDatabase(course1);
  }

  public UUID deleteCourseById(UUID id) {
    log.info("CourseService::deleteCourseById -> id passed = {}", id);
    if (!courseRepository.existsById(id)) {
      throw new CourseNotFoundException(id.toString());
    }
    courseRepository.deleteById(id);
    return id;
  }

  public List<CourseDTO> findAllCoursesByFacultyId(String id) {
    log.info("CourseService::findAllCoursesByFacultyId");
    return courseRepository.findByFaculty_Id(UUID.fromString(id)).stream()
            .map(map::courseToCourseDTO)
            .collect(Collectors.toList());
  }

  private CourseDTO saveCourseToDatabase(Course course) {
    Course savedCourse = courseRepository.save(course);
    return map.courseToCourseDTO(savedCourse);
  }

  private void validateCourseExistence(CourseDTO courseDTO) {
    Optional<Course> optionalCourse = courseRepository.findById(courseDTO.getId());
    if (optionalCourse.isPresent()) {
      throw new CourseNotFoundException(" : " + courseDTO.getName());
    }
  }
}
