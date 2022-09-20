package com.leanicontechnology.SpringBootAssessment.api.controller;

import com.leanicontechnology.SpringBootAssessment.api.dto.SubscriptionDto;
import com.leanicontechnology.SpringBootAssessment.api.dto.UserDto;
import com.leanicontechnology.SpringBootAssessment.api.service.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/v1/users")
@Slf4j
@AllArgsConstructor
public class UserController {
    UserService userService;

    @GetMapping(path = "/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(path = "/{email}")
    public ResponseEntity<UserDto> getAllUserByEmail(@PathVariable String email) {
        return new ResponseEntity<>(userService.findUserByEmail(email), HttpStatus.OK);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.createNewUser(userDto), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{userUUID}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String userUUID,
                                              @RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.updateUser(userUUID, userDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{userUUID}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable String userUUID) {
        return new ResponseEntity<>(userService.deleteUser(userUUID), HttpStatus.OK);
    }

    @PutMapping(path = "/{userUUID}/subscribe")
    public ResponseEntity<UserDto> subscribeToProduct(@PathVariable String userUUID,
                                                      @RequestBody Set<SubscriptionDto> subscriptions) {
        return new ResponseEntity<>(userService.createSubscription(userUUID, subscriptions), HttpStatus.ACCEPTED);
    }
}
