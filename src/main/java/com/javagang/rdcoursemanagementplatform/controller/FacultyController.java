package com.javagang.rdcoursemanagementplatform.controller;

import com.javagang.rdcoursemanagementplatform.model.dto.FacultyDTO;
import com.javagang.rdcoursemanagementplatform.service.FacultyService;
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
@RequestMapping("/api/v1/faculties")
@RequiredArgsConstructor
public class FacultyController {
  private final FacultyService facultyService;

  @PostMapping
  public ResponseEntity<FacultyDTO> save(@RequestBody FacultyDTO facultyDTO) {
    log.info("FacultyController::saveFaculty -> facultyDTO passed = {}", facultyDTO);
    return ResponseEntity.ok(facultyService.saveFaculty(facultyDTO));
  }

  @PutMapping("/{id}")
  public ResponseEntity<FacultyDTO> update(
      @PathVariable UUID id, @RequestBody FacultyDTO facultyDTO) {
    log.info(
        "FacultyController::updateFaculty -> id passes = {} facultyDTO passed = {}",
        id,
        facultyDTO);
    return ResponseEntity.ok(facultyService.updateFaculty(id, facultyDTO));
  }

  @GetMapping
  public ResponseEntity<List<FacultyDTO>> findAllFaculties() {
    log.info("CourseController::findAllCourses");
    return ResponseEntity.ok(facultyService.findAllFaculties());
  }

  @GetMapping("/{id}")
  public ResponseEntity<FacultyDTO> getById(@PathVariable("id") UUID id) {
    log.info("FacultyController::findFacultyById -> id passed = {}", id);
    return ResponseEntity.ok(facultyService.findFacultyById(id));
  }

  @GetMapping("/{facultyName}")
  public ResponseEntity<FacultyDTO> getByFacultyName(
      @PathVariable("facultyName") String facultyName) {
    log.info("FacultyController::getByFacultyName -> name passed = {}", facultyName);
    return ResponseEntity.ok(facultyService.findFacultyByName(facultyName));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteFacultyById(@PathVariable("id") UUID id) {
    log.info("FacultyController::deleteFaculty -> id passed = {}", id);
    return ResponseEntity.accepted().build();
  }

  @PostMapping("/{id}/add/{course_id}")
  public ResponseEntity<FacultyDTO> addCourse(
      @PathVariable("id") UUID id, @PathVariable("course_id") UUID courseId) {
    log.info(
        "FacultyController::addCourseToFaculty -> faculty id  = {} -> course id passed = {}",
        id,
        courseId);
    return ResponseEntity.ok(facultyService.addCourseToFaculty(id, courseId));
  }
}
