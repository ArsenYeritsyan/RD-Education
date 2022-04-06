package com.javagang.rdcoursemanagementplatform.service;

import com.javagang.rdcoursemanagementplatform.exception.FacultyNotFoundException;
import com.javagang.rdcoursemanagementplatform.model.entity.Course;
import com.javagang.rdcoursemanagementplatform.model.entity.Faculty;
import com.javagang.rdcoursemanagementplatform.model.mapper.MapStruct;
import com.javagang.rdcoursemanagementplatform.repository.CourseRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@MockitoSettings(strictness = Strictness.LENIENT)
class CourseServiceTest {

  @Spy private final MapStruct map = Mappers.getMapper(MapStruct.class);
  @Mock private CourseRepository courseRepository;
  @InjectMocks private CourseService courseService;

  @Test
  @Order(1)
  void findCourseById_notFound() {
    var id = UUID.randomUUID();
    when(courseRepository.findById(id))
        .thenThrow(new FacultyNotFoundException("  Test - Not found "))
        .thenReturn(Optional.empty());
    assertThrows(FacultyNotFoundException.class, () -> courseService.findCourseById(id));
    verify(courseRepository, times(1)).findById(id);
  }

  @Test
  @Order(2)
  void saveCourse() {
    var course = initCourse();
    var courseDTO1 = map.courseToCourseDTO(course);
    courseDTO1 = map.courseToCourseDTO(course);
    when(courseRepository.save(course)).thenReturn(course);
    var course1 = courseService.saveCourse(courseDTO1);
    verify(courseRepository, times(1)).save(course);
  }

  @Test
  @Order(3)
  void findCourseById() {
    var course = initCourse();
    var courseDTO1 = map.courseToCourseDTO(course);
    when(courseRepository.findById(course.getId())).thenReturn(Optional.of(course));
    var saved = courseService.findCourseById(course.getId());
    assertEquals(saved.getName(), course.getName());
    verify(courseRepository, times(1)).findById(course.getId());
  }

  @Test
  @Order(4)
  void updateCourse() {
    var course = initCourse();
    assertEquals(course.getName(), "Computer Science 1");
    when(courseRepository.save(course)).thenReturn(course);
    when(courseRepository.findById(course.getId())).thenReturn(Optional.of(course));
    course.setName("Math");
    var savedDto = courseService.updateCourse(course.getId(), map.courseToCourseDTO(course));
    assertEquals(savedDto.getName(), "Math");
    verify(courseRepository, times(1)).save(course);
  }

  @Test
  @Order(5)
  void deleteCourseById() {
    var course = initCourse();
    when(courseRepository.existsById(course.getId())).thenReturn(true);
    courseService.deleteCourseById(course.getId());
    verify(courseRepository, times(1)).deleteById(course.getId());
  }

  private Faculty createFaculty() {
    return new Faculty("CIBER Business Faculty");
  }

  private Course initCourse() {
    Course course = new Course();
    course.setName("Computer Science 1");
    course.setStartTime(new Date(2022 - 5 - 14));
    course.setFaculty(createFaculty());
    course.setId(UUID.randomUUID());
    return course;
  }
}
