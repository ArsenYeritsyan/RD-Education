package com.javagang.rdcoursemanagementplatform.service;

import com.javagang.rdcoursemanagementplatform.model.entity.User;
import com.javagang.rdcoursemanagementplatform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> Get(String mail){
        return userRepository.findBymail(mail);
    }
}
