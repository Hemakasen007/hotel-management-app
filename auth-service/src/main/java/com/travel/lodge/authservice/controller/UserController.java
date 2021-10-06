package com.travel.lodge.authservice.controller;

import com.travel.lodge.authservice.dto.CreateResponse;
import com.travel.lodge.authservice.dto.User;
import com.travel.lodge.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user-auth")
@RequiredArgsConstructor
public class UserController {

    final UserService userService;

    @PostMapping
    public ResponseEntity<CreateResponse> createUser(@RequestBody User user) {
        CreateResponse response = userService.addUser(user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
