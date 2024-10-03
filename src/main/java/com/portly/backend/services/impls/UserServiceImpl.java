package com.portly.backend.services.impls;

import com.portly.backend.dto.input.SignUpDto;
import com.portly.backend.dto.UserDto;
import com.portly.backend.entities.User;
import com.portly.backend.entities.enums.Role;
import com.portly.backend.exceptions.ResourceNotFoundException;
import com.portly.backend.repositories.UserRepository;
import com.portly.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () ->  new ResourceNotFoundException("User is not found with UserId " + userId)
        );
    }

    @Override
    public User createUser(SignUpDto signupDto) {
        Optional<User> user = userRepository.findByEmail(signupDto.getEmail());
        if(user.isPresent()){
            throw new BadCredentialsException("User with email "+ signupDto.getEmail() +" is already present.");
        }
        User userToSave = modelMapper.map(signupDto, User.class);
        userToSave.setPassword(passwordEncoder.encode(userToSave.getPassword()));
        userToSave.setRoles(List.of(Role.USER));
        return userRepository.save(userToSave);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () ->  new ResourceNotFoundException("User is not found with emailId " + email)
        );
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public UserDto getMyProfile() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public UserDto editProfile() {
        return null;
    }

    @Override
    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User with email "+ username +" is not found."));

    }
}
