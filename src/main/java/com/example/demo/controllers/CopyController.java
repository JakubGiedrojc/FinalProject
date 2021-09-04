package com.example.demo.controllers;

import com.example.demo.entities.Copy;
import com.example.demo.exceptions.MovieWithThisIdIsNotExistException;
import com.example.demo.services.AddCopyToCatalogue;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("copy")
public class CopyController {
    @Autowired
    AddCopyToCatalogue addCopyToCatalogue;

    @PostMapping("add")
    public Copy add (@RequestBody Copy copy) throws MovieWithThisIdIsNotExistException {
        return addCopyToCatalogue.add(copy.getMovie());
    }
}
