package com.example.demo.services.userServices;

import com.example.demo.entities.User;
import com.example.demo.exceptions.UsersNotFoundException;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FindAllUsers {

    private UserRepository userRepository;

    public List<User> getAllUsers() throws UsersNotFoundException {
        List<User>users=new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        if(users.isEmpty()){
            throw new UsersNotFoundException("Users NOT FOUND!");
        }
        return users;
    }
}
