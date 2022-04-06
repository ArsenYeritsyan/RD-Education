package com.javagang.rdcoursemanagementplatform.service;

import com.javagang.rdcoursemanagementplatform.exception.FacultyNotFoundException;
import com.javagang.rdcoursemanagementplatform.model.entity.Course;
import com.javagang.rdcoursemanagementplatform.model.entity.Faculty;
import com.javagang.rdcoursemanagementplatform.model.mapper.MapStruct;
import com.javagang.rdcoursemanagementplatform.repository.CourseRepository;
import com.javagang.rdcoursemanagementplatform.repository.FacultyRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@MockitoSettings(strictness = Strictness.LENIENT)
public class FacultyServiceTest {

  @Spy private final MapStruct map = Mappers.getMapper(MapStruct.class);
  @Mock private FacultyRepository facultyRepository;
  @Mock private CourseRepository courseRepository;
  @InjectMocks private FacultyService facultyService;

  @Test
  @Order(1)
  void findFacultyById_notFound() {
    var id = UUID.randomUUID();
    when(facultyRepository.findById(id))
        .thenThrow(new FacultyNotFoundException("  Test - Not found "))
        .thenReturn(Optional.empty());
    assertThrows(FacultyNotFoundException.class, () -> facultyService.findFacultyById(id));
    verify(facultyRepository, times(1)).findById(id);
  }

  @Test
  @Order(2)
  void saveFaculty() {
    var faculty = createFaculty();
    var fDto = map.facultyToFacultyDTO(faculty);
    when(facultyRepository.save(faculty)).thenReturn(faculty);
    var saved = facultyService.saveFaculty(fDto);
    assertEquals(saved.getFacultyName(), fDto.getFacultyName());
    verify(facultyRepository, times(1)).save(faculty);
  }

  @Test
  void findFacultyById() {
    var faculty = createFaculty();
    when(facultyRepository.findById(faculty.getId())).thenReturn(Optional.of(faculty));
    var facultyById = facultyService.findFacultyById(faculty.getId());
    verify(facultyRepository, times(1)).findById(faculty.getId());
  }

  @Test
  void updateFaculty() {
    var faculty = createFaculty();
    when(facultyRepository.findById(faculty.getId())).thenReturn(Optional.of(faculty));
    when(facultyRepository.save(faculty)).thenReturn(faculty);
    faculty.setFacultyName("Math");
    var savedDto = facultyService.updateFaculty(faculty.getId(), map.facultyToFacultyDTO(faculty));
    verify(facultyRepository, times(1)).findById(faculty.getId());
    assertEquals(savedDto.getFacultyName(), "Math");
  }

  @Test
  void addCourseToFaculty() {
    var faculty = createFaculty();
    var fDto = map.facultyToFacultyDTO(faculty);
    when(facultyRepository.findById(faculty.getId())).thenReturn(Optional.of(faculty));
    when(facultyRepository.save(faculty)).thenReturn(faculty);
    var testCourse = initCourse();
    when(courseRepository.findById(testCourse.getId())).thenReturn(Optional.of(testCourse));
    when(courseRepository.save(testCourse)).thenReturn(testCourse);
    var course = courseRepository.save(testCourse);
    fDto.getCourses().add(map.courseToCourseDTO(testCourse));
    facultyService.addCourseToFaculty(faculty.getId(), course.getId());
    assertFalse(facultyService.findFacultyById(faculty.getId()).getCourses().isEmpty());
  }

  @Test
  void deleteFacultyById() {
    var faculty = createFaculty();
    when(facultyRepository.findById(faculty.getId())).thenReturn(Optional.of(faculty));
    facultyService.deleteFacultyById(faculty.getId());
    verify(facultyRepository, times(1)).deleteById(faculty.getId());
  }

  private Faculty createFaculty() {
    var faculty = new Faculty();
    faculty.setFacultyName("CIBER Business Faculty");
    faculty.setId(UUID.randomUUID());
    return faculty;
  }

  private Course initCourse() {
    Course course = new Course();
    course.setName("Computer Science 1");
    course.setStartTime(new Date(2022 - 5 - 14));
    course.setId(UUID.randomUUID());
    return course;
  }
}
