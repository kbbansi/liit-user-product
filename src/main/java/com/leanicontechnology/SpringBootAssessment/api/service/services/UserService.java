package com.leanicontechnology.SpringBootAssessment.api.service.services;

import com.leanicontechnology.SpringBootAssessment.api.dto.SubscriptionDto;
import com.leanicontechnology.SpringBootAssessment.api.dto.UserDto;

import java.util.List;
import java.util.Set;

public interface UserService {
    UserDto createNewUser(UserDto user);

    UserDto findUserByEmail(String email);

    List<UserDto> getAllUsers();

    UserDto updateUser(String userUUID,UserDto user);

    UserDto deleteUser(String userUUID);

    UserDto createSubscription(String userUUID, Set<SubscriptionDto> subscriptions);
}
