package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.exceptions.UserAlreadyExistException;
import com.example.demo.services.UserRegistration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
    @Autowired
    UserRegistration userRegistration;

    @PostMapping("add")
    public User registration(@RequestBody User user) throws UserAlreadyExistException {
        return userRegistration.registration(user);


    }
}
