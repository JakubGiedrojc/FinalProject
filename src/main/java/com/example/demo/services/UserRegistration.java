package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.exceptions.ErrorMessage;
import com.example.demo.exceptions.UserAlreadyExistException;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserRegistration {

    @Autowired
    UserRepository userRepository;
    ErrorMessage errorMessage;

    public User registration(User user) throws UserAlreadyExistException {
        if(userRepository.findByLogin(user.getLogin()) !=null){
            throw new UserAlreadyExistException(errorMessage);
        }
        if(userRepository.findByEmailAddress(user.getEmailAddress()) !=null){
            throw new UserAlreadyExistException(errorMessage);
        }
        return userRepository.save(user);

    }
}
