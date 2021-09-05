package com.example.demo.services.copyService;

import com.example.demo.entities.Copy;
import com.example.demo.exceptions.CopiesNotFoundException;
import com.example.demo.repositories.CopyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FindAllCopies {

    private CopyRepository copyRepository;
    public List<Copy> getAllCopies() throws CopiesNotFoundException {
        List<Copy>copies=new ArrayList<>();
        copyRepository.findAll().forEach(copies::add);
        if(copies.isEmpty()){
            throw new CopiesNotFoundException("Copies NOT FOUND!");
        }
        return copies;
    }
}
