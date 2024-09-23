package com.portly.backend.services.impls;

import com.portly.backend.dto.*;
import com.portly.backend.dto.input.ForgetPasswordDto;
import com.portly.backend.entities.User;
import com.portly.backend.services.AuthService;
import com.portly.backend.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final UserServiceImpl userService;
    private final JwtService jwtService;
    private final SessionServiceImpl sessionService;
    private final PortfolioServiceImpl portfolioService;


    @Override
    @Transactional
    public UserDto signup(SignUpDto signupDto) {
        User user = userService.createUser(signupDto);
        portfolioService.createPortfolio(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public LoginResponseDto login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );

        User user = (User) authentication.getPrincipal();

        String accessToken =  jwtService.generateAccessToken(user);
        String refreshToken =  jwtService.generateRefreshToken(user);

        sessionService.createSession(user, refreshToken);

        return new LoginResponseDto(user.getId(), accessToken, refreshToken, authentication.isAuthenticated());

    }

    @Override
    public UserDto forgetPassword(ForgetPasswordDto forgetPasswordDto) {
        return null;
    }

    @Override
    public LoginResponseDto refresh(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        sessionService.validateSession(refreshToken);
        User user = userService.findUserById(userId);

        String accessToken =  jwtService.generateAccessToken(user);
        return new LoginResponseDto(userId, accessToken, refreshToken, true);
    }
}
