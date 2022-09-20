package com.leanicontechnology.SpringBootAssessment.api.service.services;

import com.leanicontechnology.SpringBootAssessment.api.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createNewUser(UserDto user);

    UserDto findUserByEmail(String email);

    List<UserDto> getAllUsers();

    UserDto updateUser(String userUUID,UserDto user);

    UserDto deleteUser(String userUUID);

    //getUserSubscriptions
}
