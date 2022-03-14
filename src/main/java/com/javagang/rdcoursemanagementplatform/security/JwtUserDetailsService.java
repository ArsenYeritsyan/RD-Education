package com.javagang.rdcoursemanagementplatform.security;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import com.javagang.rdcoursemanagementplatform.model.dto.RegisterFormDTO;
import com.javagang.rdcoursemanagementplatform.model.entity.User;
import com.javagang.rdcoursemanagementplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

//    TODO must be changed to return UserDTO instead of User
    public User save(RegisterFormDTO registerForm) {
        User newUser = new User();
        newUser.setPassword(bcryptEncoder.encode(registerForm.getPassword()));
        newUser.setDob(registerForm.getDob());
        newUser.setFirstName(registerForm.getFirstName());
        newUser.setLastName(registerForm.getLastName());
        newUser.setMail(registerForm.getMail());

        return userRepository.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        var user = userRepository.findBymail(mail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + mail));

        return new org.springframework.security.core.userdetails.User(user.getMail(), user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getRoleType().toString()))
                        .collect(Collectors.toList()));
    }
}
