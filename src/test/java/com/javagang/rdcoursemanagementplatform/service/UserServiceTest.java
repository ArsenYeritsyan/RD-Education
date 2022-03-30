package com.javagang.rdcoursemanagementplatform.service;

import com.javagang.rdcoursemanagementplatform.exception.UserNotFoundException;
import com.javagang.rdcoursemanagementplatform.mapper.UserMapper;
import com.javagang.rdcoursemanagementplatform.model.dto.UserDTO;
import com.javagang.rdcoursemanagementplatform.model.entity.User;
import com.javagang.rdcoursemanagementplatform.repository.UserRepository;
import com.javagang.rdcoursemanagementplatform.security.JwtTokenUtil;
import com.javagang.rdcoursemanagementplatform.utility.MailUtility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
class UserServiceTest {

    @Autowired
    private UserService userService;

    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private JwtTokenUtil jwtUtil;

    @MockBean
    private MailUtility javaMailUtil;

    @Test
    void getUserByEmail_Ok() {
        String email = "davo@gmail.com";
        when(userRepository.findByMail(email)).thenReturn(Optional.of(getUser()));

        var result = userService.getUserByEmail(email);

        assertEquals(getUserDTO(), result);
    }

    @Test
    void getUserByEmail_NotFound() {
        String email = "davo@gmail.com";

        Assertions.assertThrows(UserNotFoundException.class, () -> userService.getUserByEmail(email));
    }

    private User getUser() {
        var user = new User();
        user.setMail("davo@gmail.com");
        user.setPassword("barev2022");
        user.setPictureId("t85_po21Lk");
        user.setFirstName("Davit");
        user.setLastName("Baghoyan");

        return user;
    }

    private UserDTO getUserDTO() {
        var userDTO = new UserDTO();
        userDTO.setMail("davo@gmail.com");
        userDTO.setPictureId("t85_po21Lk");
        userDTO.setFirstName("Davit");
        userDTO.setLastName("Baghoyan");

        return userDTO;
    }
}