package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);
    List<UserEntity> getAllUsers();
    UserEntity getUserByIdOrElseThrow(String userId);
    UserEntity getUserByEmailOrElseThrow(String email);
}
