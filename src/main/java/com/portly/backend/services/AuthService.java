package com.portly.backend.services;


import com.portly.backend.dto.*;
import com.portly.backend.dto.input.ForgetPasswordDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    UserDto signup(SignUpDto signupDto);

    LoginResponseDto login(LoginDto loginDto);

    UserDto forgetPassword(ForgetPasswordDto forgetPasswordDto);

    LoginResponseDto refresh(String refreshToken);

}
