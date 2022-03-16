package com.javagang.rdcoursemanagementplatform.controller;

import com.javagang.rdcoursemanagementplatform.model.dto.UserDTO;
import com.javagang.rdcoursemanagementplatform.model.entity.User;
import com.javagang.rdcoursemanagementplatform.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
    private static final ModelMapper modelMapper = new ModelMapper();
    private final UserService userService;

    @GetMapping("/users/{mail}")
    public ResponseEntity<?> Get(@PathVariable String mail) throws Exception {
        User user = userService
                .Get(mail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return ResponseEntity.ok(modelMapper.map(user, UserDTO.class));
    }

}
