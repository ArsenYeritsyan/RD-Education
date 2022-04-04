package com.javagang.rdcoursemanagementplatform.service;

import com.javagang.rdcoursemanagementplatform.exception.CourseEnrollmentException;
import com.javagang.rdcoursemanagementplatform.exception.UserNotFoundException;
import com.javagang.rdcoursemanagementplatform.helper.FeignClientInterceptor;
import com.javagang.rdcoursemanagementplatform.model.entity.Course;
import com.javagang.rdcoursemanagementplatform.model.entity.Student;
import com.javagang.rdcoursemanagementplatform.repository.CourseRepository;
import com.javagang.rdcoursemanagementplatform.repository.FacultyRepository;
import com.javagang.rdcoursemanagementplatform.repository.StudentRepository;
import com.javagang.rdcoursemanagementplatform.security.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

    private final CourseRepository courseRepository;
    private final JwtTokenUtil jwtUtil;
    private final StudentRepository studentRepository;

    public Student enrollCourse(List<UUID> courseIds) {

        int size = courseIds.size();

        if (size > 6) {
            throw new CourseEnrollmentException("User can enroll not more than 6 courses");
        }

        List<Course> courses = courseRepository.findAllById(courseIds);

        var filteredCourses = courses
                .stream()
                .filter(c -> {
                    for (Course cr : courses) {
                        if (cr.getStartDate().compareTo(c.getStartDate()) * c.getStartDate().compareTo(cr.getEndDate()) > 0
                                || cr.getStartDate().compareTo(c.getEndDate()) * c.getEndDate().compareTo(cr.getEndDate()) > 0) {
                            throw new CourseEnrollmentException("Selected courses are incompatible.");
                        }
                    }
                    return c.getFaculty().equals(courses.get(0).getFaculty());
                }).collect(Collectors.toList());

        if (filteredCourses.size() != size) {
            throw new CourseEnrollmentException("User can enroll the courses of the same faculty.");
        }

        String token = FeignClientInterceptor.getBearerTokenHeader();
        String username = jwtUtil.getUsernameFromToken(token);

        Student student = studentRepository
                .findByMail(username)
                .orElseThrow(() -> {throw new UserNotFoundException("User not found.");});

        HashSet<Course> cc = new HashSet<>(courses);
        student.getCourses().addAll(cc);
        return studentRepository.save(student);
    }
}
