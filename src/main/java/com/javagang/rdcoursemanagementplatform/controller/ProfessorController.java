package com.javagang.rdcoursemanagementplatform.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import com.javagang.rdcoursemanagementplatform.service.ProfessorService;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/professors")
public class ProfessorController {

  private final ProfessorService service;

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping("/{professorId}/assign")
  public ResponseEntity<?> assign(
      @PathVariable("professorId") UUID professorId, @RequestParam("courseId") UUID courseId) {
    service.assign(professorId, courseId);
    return ResponseEntity.ok().build();
  }
}
