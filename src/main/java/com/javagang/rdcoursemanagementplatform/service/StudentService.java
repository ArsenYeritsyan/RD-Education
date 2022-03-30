package com.javagang.rdcoursemanagementplatform.service;

import com.javagang.rdcoursemanagementplatform.exception.CourseEnrollmentException;
import com.javagang.rdcoursemanagementplatform.helper.FeignClientInterceptor;
import com.javagang.rdcoursemanagementplatform.model.entity.Course;
import com.javagang.rdcoursemanagementplatform.model.entity.Faculty;
import com.javagang.rdcoursemanagementplatform.model.entity.Student;
import com.javagang.rdcoursemanagementplatform.repository.CourseRepository;
import com.javagang.rdcoursemanagementplatform.repository.FacultyRepository;
import com.javagang.rdcoursemanagementplatform.repository.StudentRepository;
import com.javagang.rdcoursemanagementplatform.security.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

    private final FeignClientInterceptor feignClientInterceptor;
    private final CourseRepository courseRepository;
    private final JwtTokenUtil jwtUtil;
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final PasswordEncoder bcryptEncoder;

    public Student enrollCourse(List<UUID> courseIds) {
       Faculty f = new Faculty();
        f.setFacultyName("IKM");
        var faculty = facultyRepository.save(f);
/*
        Course c1 = new Course();
        c1.setEndDate(new Date(2022, 12, 12));
        c1.setName("Java Course");
        c1.setStartDate(new Date(2022, 11, 11));
        c1.setFaculty(faculty);
        Course savedCourse = courseRepository.save(c1);
*/

        int size = courseIds.size();

        if (size > 6) {
            throw new CourseEnrollmentException("User can enroll not more than 6 courses");
        }
/*
        var courses = courseIds
                .stream()
                .map(id -> courseRepository.getById(id))
                .collect(Collectors.toList());

        var filteredCourses = courses
                .stream()
                .filter(c -> {
                    for (Course cr : courses) {
                        if (cr.getStartDate().compareTo(c.getStartDate()) * c.getStartDate().compareTo(cr.getEndDate()) > 0
                                && cr.getStartDate().compareTo(c.getEndDate()) * c.getEndDate().compareTo(cr.getEndDate()) > 0) {
                            return false;
                        }
                    }
                    return c.getFaculty().equals(courses.get(0).getFaculty());
                }).collect(Collectors.toList());

        if (filteredCourses.size() != size) {
            throw new CourseEnrollmentException("User can enroll the courses of the same faculty");
        }
*/

        String token = FeignClientInterceptor.getBearerTokenHeader();
        String username = jwtUtil.getUsernameFromToken(token);
      /*  Student s2 = new Student();
        s2.setStartDate(LocalDate.of(2022,5,5));
        s2.setPassword(bcryptEncoder.encode("dav1999"));
        s2.setFirstName("Davo");
        s2.setLastName("Baghoyan");
        s2.setMail("dav555745550105144472200147@mail.ru");
        s2.setDob(LocalDate.of(2022,5,5));
        studentRepository.save(s2);
*/
        Student student = studentRepository
                .findByMail(username)
                .orElseThrow();

    //    student.getCourses().addAll(filteredCourses);

        List<Course> courses = courseRepository.findAllById(courseIds);

        HashSet<Course> cc = new HashSet<>(courses);
        student.getCourses().addAll(cc);
        return studentRepository.save(student);
    }
}
