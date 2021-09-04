package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.exceptions.UserAlreadyExistException;
import com.example.demo.exceptions.UserNotExistException;
import com.example.demo.exceptions.UsersNotFoundException;
import com.example.demo.services.userServices.FindAllUsers;
import com.example.demo.services.userServices.UserDeleted;
import com.example.demo.services.userServices.UserRegistration;
import com.example.demo.services.userServices.UserUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {


    private UserRegistration userRegistration;
    private FindAllUsers findAllUsers;
    private UserUpdate userUpdate;
    private UserDeleted userDeleted;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("add")
    public User registration(@RequestBody User user) throws UserAlreadyExistException {
        return userRegistration.registration(user);
    }

    @GetMapping("get-all")
    public List<User> getAllUsers() throws UsersNotFoundException {
        return findAllUsers.getAllUsers();
    }

    @PutMapping("update")
    public User update(@RequestBody User user) {
        return this.userUpdate.update(user);
    }
    @DeleteMapping("delete")
    public void delete(@PathVariable User user) throws UserNotExistException {
        this.userDeleted.delete(user.getId());
    }
}
