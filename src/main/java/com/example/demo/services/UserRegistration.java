package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.exceptions.UserAlredyExistException;


@Service
public class UserRegistration {

    @Autowired
    UserRepository userRepository;

    public User registration(User user){
        if(userRepository.findByLogin(user.getLogin()) !=null){
            throw new UserAlredyExistException(user.getLogin());
        }
        if(userRepository.findByEmailAddress(user.getEmailAddress()) !=null){
            throw new UserAlreadyExistException(user.getEmailAddress());
        }
        return userRepository.save(user);

    }
}
