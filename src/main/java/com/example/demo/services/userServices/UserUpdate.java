package com.example.demo.services.userServices;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserUpdate {

    private UserRepository userRepository;

    public User update(User user) {
        final int id=user.getId();
        if(id<0){
            throw new IllegalArgumentException();
        }
        User entity = this.userRepository.findById(id)
                .orElseThrow(() ->new IllegalArgumentException("User with id"+id+"doesnt exist"));

        entity.setAddresses(user.getAddresses());
        entity.setEmailAddress(user.getEmailAddress());
        entity.setPassword(user.getPassword());
        entity.setLogin(user.getLogin());

        return this.userRepository.save(entity);
    }
}
