package com.leanicontechnology.SpringBootAssessment.api.service.impl;

import com.leanicontechnology.SpringBootAssessment.api.dto.SubscriptionDto;
import com.leanicontechnology.SpringBootAssessment.api.dto.UserDto;
import com.leanicontechnology.SpringBootAssessment.api.service.services.UserService;
import com.leanicontechnology.SpringBootAssessment.data.entity.UserEntity;
import com.leanicontechnology.SpringBootAssessment.data.repository.SubscriptionRepository;
import com.leanicontechnology.SpringBootAssessment.data.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    ModelMapper mapper = new ModelMapper();

    @Autowired
    UserRepository userRepository;

    @Autowired
    SubscriptionRepository subscriptionRepository;


    /**
     * @param user user object to be created
     * @return created user
     */
    @Override
    public UserDto createNewUser(UserDto user) {
        log.info("Create User Service; Request Body: [{}]", user);
        var userAlreadyExists = userRepository.findUserEntityByEmail(user.getEmail())
                .stream()
                .map(userEntity -> userEntity.getEmail().equals(user.getEmail()) || userEntity.getUserName().equals(user.getUserName()))
                .findFirst()
                .orElse(Boolean.FALSE);

        if (!userAlreadyExists) {
            var userEntity = UserEntity.builder()
                    .userName(user.getUserName())
                    .userUUID(UUID.randomUUID().toString())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .dateOfBirth(user.getDateOfBirth())
                    .otherNames(user.getOtherNames())
                    .email(user.getEmail())
                    .imageUrl(user.getImageUrl())
                    .createdOn(LocalDateTime.now())
                    .isActive(true)
                    .build();

            return  mapper.map(userRepository.save(userEntity), UserDto.class);
        } else {
            throw new RuntimeException("User with email: " + user.getEmail() + " and user name: " + user.getUserName() +" already exists");
        }

    }

    @Override
    public UserDto findUserByEmail(String email) {
        log.info("Find User By Email; Email: [{}]", email);
        var userEntity = userRepository.findUserEntityByEmail(email).orElseThrow();
        return mapper.map(userEntity, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        log.info("Get All Users Service");
        return userRepository.findAll()
                .stream()
                .map(userEntity -> mapper.map(userEntity, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(String userUUID, UserDto user) {
        log.info("Update User Service; Request Body: [{}]", user);
        var userEntity = userRepository.findByUserUUID(userUUID)
                .orElseThrow(()-> new RuntimeException("No such user with ID: " + userUUID + " found"));

        userEntity.setDateOfBirth(user.getDateOfBirth());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setModifiedOn(LocalDateTime.now());
        var updatedUser = userRepository.save(userEntity);
        return mapper.map(updatedUser, UserDto.class);
    }

    @Override
    public UserDto deleteUser(String userUUID) {
        log.info("Delete User Service; ID: [{}]", userUUID);
        var userEntity = userRepository.findByUserUUID(userUUID)
                .orElseThrow(() -> new RuntimeException("No such user with ID: " + userUUID + " found"));
        userEntity.setIsActive(false);
        return mapper.map(userRepository.save(userEntity), UserDto.class);
    }

    /**
     * @param userUUID user uuid
     * @param subscriptions set of subscriptions
     * @return user
     */
    @Override
    public UserDto createSubscription(String userUUID, Set<SubscriptionDto> subscriptions) {
        var userEntity = userRepository.findByUserUUID(userUUID).orElseThrow(() -> new RuntimeException("No such user with ID: " + userUUID + " found"));

        var subscriptionEntity = subscriptions.stream()
                .map(subscription -> subscriptionRepository.findBySubscriptionUUID(subscription.getSubscriptionUUID()).orElseThrow(RuntimeException::new))
                .collect(Collectors.toSet());
        userEntity.setSubscriptions(subscriptionEntity);
        return mapper.map(userRepository.save(userEntity), UserDto.class);
    }
}
