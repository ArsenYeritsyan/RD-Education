package com.javagang.rdcoursemanagementplatform.controller;

import com.javagang.rdcoursemanagementplatform.model.dto.CourseDTO;
import com.javagang.rdcoursemanagementplatform.service.CourseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
//@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/{facultyId}")
    public ResponseEntity<List<CourseDTO>> getAllCoursesByFacultyId(@RequestParam("facultyId") String id) {
        List<CourseDTO> courseList = courseService.findAllCoursesByFacultyId(id);
        return ResponseEntity.ok(courseList);
    }

    @PostMapping
    public ResponseEntity<CourseDTO> save(@RequestBody CourseDTO courseDTO) {
        log.info("CourseController::saveCourse -> courseDTO passed = {}", courseDTO);
        return ResponseEntity.ok(courseService.saveCourse(courseDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> update(
            @PathVariable("id") UUID id, @RequestBody CourseDTO courseDTO) {
        log.info(
                "CourseController::updateCourse -> id passes = {} courseDTO passed = {}", id, courseDTO);
        return ResponseEntity.ok(courseService.updateCourse(id, courseDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getById(@PathVariable UUID id) {
        log.info("CourseController::findCourseById -> id passed = {}", id);
        return ResponseEntity.ok(courseService.findCourseById(id));
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> findAllCourses() {
        log.info("CourseController::findAllCourses");
        return ResponseEntity.ok(courseService.findAllCourses());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourseById(@PathVariable("id") UUID id) {
        log.info("CourseController::deleteCourse -> id passed = {}", id);
        return ResponseEntity.ok(courseService.deleteCourseById(id));
    }
}
