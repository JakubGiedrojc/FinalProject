package com.example.demo.services.copyService;

import com.example.demo.exceptions.CopyNotExistException;
import com.example.demo.repositories.CopyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CopyDeleted {

    private CopyRepository copyRepository;

    public void delete(int id) throws CopyNotExistException {
        if(copyRepository.findById(id).isEmpty()){
            throw new CopyNotExistException("Copy with this id" + "NOT FOUND!");
        }
        this.copyRepository.deleteById(id);
    }
}
