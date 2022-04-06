package com.javagang.rdcoursemanagementplatform.service;

import com.javagang.rdcoursemanagementplatform.exception.CourseEnrollmentException;
import com.javagang.rdcoursemanagementplatform.exception.UserNotFoundException;
import com.javagang.rdcoursemanagementplatform.model.entity.Course;
import com.javagang.rdcoursemanagementplatform.model.entity.Student;
import com.javagang.rdcoursemanagementplatform.repository.CourseRepository;
import com.javagang.rdcoursemanagementplatform.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final StudentRepository studentRepository;


    public Student enrollCourse(List<UUID> courseIds) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username= "";

        if(authentication != null){
            username = authentication.getName();
        }

        Student student = studentRepository
                .findByMail(username)
                .orElseThrow(() -> {throw new UserNotFoundException("User not found.");});

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
                    return c.getFaculty().equals(student.getFaculty());
                }).collect(Collectors.toList());

        if (filteredCourses.size() != size) {
            throw new CourseEnrollmentException("User can enroll the courses of the same faculty.");
        }

        HashSet<Course> cc = new HashSet<>(courses);
        student.getCourses().addAll(cc);
        return studentRepository.save(student);
    }
}
