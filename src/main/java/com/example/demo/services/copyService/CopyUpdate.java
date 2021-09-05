package com.example.demo.services.copyService;

import com.example.demo.entities.Copy;
import com.example.demo.repositories.CopyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CopyUpdate {

    private CopyRepository copyRepository;

    public Copy update(Copy copy) {
        final int id=copy.getId();
        if(id<0){
            throw new IllegalArgumentException();
        }
        Copy c = this.copyRepository.findById(id)
                .orElseThrow(() ->new IllegalArgumentException("Copy with id"+id+"doesnt exist"));

        c.setMovie(copy.getMovie());
        c.setOrder(copy.getOrder());

        return this.copyRepository.save(c);
    }
}
