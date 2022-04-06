package com.javagang.rdcoursemanagementplatform.service;

import com.javagang.rdcoursemanagementplatform.exception.CourseEnrollmentException;
import com.javagang.rdcoursemanagementplatform.model.entity.Course;
import com.javagang.rdcoursemanagementplatform.model.entity.Faculty;
import com.javagang.rdcoursemanagementplatform.model.entity.Student;
import com.javagang.rdcoursemanagementplatform.repository.CourseRepository;
import com.javagang.rdcoursemanagementplatform.repository.StudentRepository;
import com.javagang.rdcoursemanagementplatform.security.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {StudentService.class, StudentRepository.class, CourseRepository.class, JwtTokenUtil.class})
class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private CourseRepository courseRepository;

    @MockBean
    private JwtTokenUtil jwtUtil;

    @Test
    public void enrollCourse_over_six() {
        List<UUID> ids = new ArrayList<>(7);
        ids.add(UUID.randomUUID());
        ids.add(UUID.randomUUID());
        ids.add(UUID.randomUUID());
        ids.add(UUID.randomUUID());
        ids.add(UUID.randomUUID());
        ids.add(UUID.randomUUID());
        ids.add(UUID.randomUUID());
        when(studentRepository.findByMail(any(String.class))).thenReturn(Optional.of(getStudent()));

        assertThrows(CourseEnrollmentException.class, () -> studentService.enrollCourse(ids));
    }

    @Test
    public void enrollCourse_different_faculties() {
        Faculty f1 = new Faculty("IKM");
        f1.setId(UUID.randomUUID());
        f1.setCourses(new HashSet<>());
        f1.setProfessors(new HashSet<>());
        Faculty f2 = new Faculty("HHK");
        f2.setId(UUID.randomUUID());
        f2.setCourses(new HashSet<>());
        f2.setProfessors(new HashSet<>());
        List<UUID> ids = new ArrayList<>();
        ids.add(UUID.randomUUID());
        ids.add(UUID.randomUUID());
        Course c1 = new Course();
        Course c2 = new Course();
        c1.setFaculty(f1);
        c1.setId(UUID.randomUUID());
        c1.setStartDate(LocalDateTime.of(2022, Month.MAY, 22, 10, 10, 10));
        c1.setEndDate(LocalDateTime.of(2022, 6, 25, 10, 10, 10));
        c1.setName("Java");
        c1.setHomeworks(new HashSet<>());
        c1.setStudents(new HashSet<>());
        c2.setFaculty(f2);
        c2.setId(UUID.randomUUID());
        c2.setStartDate(LocalDateTime.of(2022, 7, 22, 10, 10, 10));
        c2.setEndDate(LocalDateTime.of(2022, 8, 25, 10, 10, 10));
        c2.setName("JS");
        c2.setHomeworks(new HashSet<>());
        c2.setStudents(new HashSet<>());
        List<Course> courses = List.of(c1, c2);
        when(courseRepository.findAllById(ids)).thenReturn(courses);
        when(studentRepository.findByMail(any(String.class))).thenReturn(Optional.of(getStudent()));

        assertThrows(CourseEnrollmentException.class, () -> studentService.enrollCourse(ids));
    }


    @Test
    public void enrollCourse_dates_conflict() {
        Faculty f1 = new Faculty("IKM");
        f1.setCourses(new HashSet<>());
        f1.setProfessors(new HashSet<>());
        List<UUID> ids = new ArrayList<>();
        ids.add(UUID.randomUUID());
        ids.add(UUID.randomUUID());
        Course c1 = new Course();
        Course c2 = new Course();
        c1.setFaculty(f1);
        c1.setId(UUID.randomUUID());
        c1.setStartDate(LocalDateTime.of(2022, 5, 22, 10, 10, 10));
        c1.setEndDate(LocalDateTime.of(2022, 6, 25, 10, 10, 10));
        c1.setName("Java");
        c1.setHomeworks(new HashSet<>());
        c1.setStudents(new HashSet<>());
        c2.setFaculty(f1);
        c2.setId(UUID.randomUUID());
        c2.setStartDate(LocalDateTime.of(2022, 5, 27, 10, 10, 10));
        c2.setEndDate(LocalDateTime.of(2022, 6, 17, 10, 10, 10));
        c2.setName("JS");
        c2.setHomeworks(new HashSet<>());
        c2.setStudents(new HashSet<>());
        List<Course> courses = List.of(c1, c2);
        when(courseRepository.findAllById(ids)).thenReturn(courses);
        when(studentRepository.findByMail(any(String.class))).thenReturn(Optional.of(getStudent()));

        assertThrows(CourseEnrollmentException.class, () -> studentService.enrollCourse(ids));
    }

    @Test
    public void enrollCourse_Ok() {
        Faculty f1 = new Faculty("FTY");
        f1.setCourses(new HashSet<>());
        f1.setProfessors(new HashSet<>());
        List<UUID> ids = new ArrayList<>();
        ids.add(UUID.randomUUID());
        ids.add(UUID.randomUUID());
        Course c1 = new Course();
        Course c2 = new Course();
        c1.setFaculty(f1);
        c1.setId(UUID.randomUUID());
        c1.setStartDate(LocalDateTime.of(2022, 5, 22, 10, 10, 10));
        c1.setEndDate(LocalDateTime.of(2022, 6, 25, 10, 10, 10));
        c1.setName("Java");
        c1.setHomeworks(new HashSet<>());
        c1.setStudents(new HashSet<>());
        c2.setFaculty(f1);
        c2.setId(UUID.randomUUID());
        c2.setStartDate(LocalDateTime.of(2022, 8, 27, 10, 10, 10));
        c2.setEndDate(LocalDateTime.of(2022, 9, 17, 10, 10, 10));
        c2.setName("JS");
        c2.setHomeworks(new HashSet<>());
        c2.setStudents(new HashSet<>());
        List<Course> courses = List.of(c1, c2);
        var expectedStudent = getStudent();
        expectedStudent.getCourses().addAll(courses);
        when(courseRepository.findAllById(ids)).thenReturn(courses);
        when(studentRepository.findByMail(any(String.class))).thenReturn(Optional.of(getStudent()));
        when(studentRepository.save(any(Student.class))).thenReturn(expectedStudent);

        var result = studentService.enrollCourse(ids);

        assertEquals(new HashSet<>(courses), result.getCourses());
    }

    private Student getStudent() {
        var student = new Student();
        student.setMail("johnson@gmail.com");
        student.setPassword("hello2022");
        student.setPictureId("t85_po21Lk");
        student.setFirstName("John");
        student.setLastName("Johnson");
        Faculty f = new Faculty("FTY");
        f.setCourses(new HashSet<>());
        f.setProfessors(new HashSet<>());
        student.setFaculty(f);
        return student;
    }

}