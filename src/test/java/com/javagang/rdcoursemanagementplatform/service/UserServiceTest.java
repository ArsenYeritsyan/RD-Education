package com.javagang.rdcoursemanagementplatform.service;

import com.javagang.rdcoursemanagementplatform.exception.UserNotFoundException;
import com.javagang.rdcoursemanagementplatform.mapper.UserMapper;
import com.javagang.rdcoursemanagementplatform.mapper.UserMapperImpl;
import com.javagang.rdcoursemanagementplatform.model.dto.UserDTO;
import com.javagang.rdcoursemanagementplatform.model.entity.User;
import com.javagang.rdcoursemanagementplatform.repository.UserRepository;
import com.javagang.rdcoursemanagementplatform.security.JwtTokenUtil;
import com.javagang.rdcoursemanagementplatform.utility.MailUtility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {UserService.class, UserRepository.class, PasswordEncoder.class, JwtTokenUtil.class, MailUtility.class, UserMapperImpl.class})
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private JwtTokenUtil jwtUtil;

    @MockBean
    private MailUtility javaMailUtil;

    @Test
    public void getUserByEmail_Ok() {
        String email = "johnson@gmail.com";
        when(userRepository.findByMail(email)).thenReturn(Optional.of(getUser()));

        var result = userService.getUserByEmail(email);

        assertEquals(getUserDTO(), result);
    }

    @Test
    public void getUserByEmail_NotFound() {
        String email = "johnson@gmail.com";

        assertThrows(UserNotFoundException.class, () -> userService.getUserByEmail(email));
    }

    private User getUser() {
        var user = new User();
        user.setMail("johnson@gmail.com");
        user.setPassword("hello2022");
        user.setPictureId("t85_po21Lk");
        user.setFirstName("John");
        user.setLastName("Johnson");

        return user;
    }

    private UserDTO getUserDTO() {
        var userDTO = new UserDTO();
        userDTO.setMail("johnson@gmail.com");
        userDTO.setPassword("hello2022");
        userDTO.setPictureId("t85_po21Lk");
        userDTO.setFirstName("John");
        userDTO.setLastName("Johnson");

        return userDTO;
    }
}