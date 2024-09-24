package com.portly.backend.services;

import com.portly.backend.dto.input.SignUpDto;
import com.portly.backend.dto.UserDto;
import com.portly.backend.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {

    User findUserById(Long userId);

    User createUser(SignUpDto signupDto);

    User findUserByEmail(String email);

    UserDto getMyProfile();

    UserDto editProfile();

    User getCurrentUser();
}
