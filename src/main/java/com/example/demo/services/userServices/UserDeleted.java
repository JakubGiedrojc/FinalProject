package com.example.demo.services.userServices;


import com.example.demo.exceptions.UserNotExistException;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDeleted {

    UserRepository userRepository;

    public void delete(int id) throws UserNotExistException {
        if(userRepository.findById(id).isEmpty()) {
            throw new UserNotExistException("User with this id" + id + "is not exist");
        }
        this.userRepository.deleteById(id);
    }
}
