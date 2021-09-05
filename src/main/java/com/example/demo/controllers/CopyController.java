package com.example.demo.controllers;

import com.example.demo.entities.Copy;
import com.example.demo.entities.Movie;
import com.example.demo.exceptions.CopiesNotFoundException;
import com.example.demo.exceptions.CopyNotExistException;
import com.example.demo.exceptions.MovieWithThisIdIsNotExistException;
import com.example.demo.services.copyService.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("copy")
public class CopyController {

    private AddCopyToCatalogue addCopyToCatalogue;
    private FindAllCopies findAllCopies;
    private CopyUpdate copyUpdate;
    private CopyDeleted copyDeleted;
    private FindAllCopiesByMovie findAllCopiesByMovie;

    @PostMapping("add")
    public Copy add (@RequestBody Copy copy) throws MovieWithThisIdIsNotExistException {
        return addCopyToCatalogue.add(copy.getMovie());
    }

    @GetMapping("get-all")
    public List<Copy> getAllCopies() throws CopiesNotFoundException {
        return findAllCopies.getAllCopies();
    }
    @GetMapping("get-all-by-movie")
    public List<Copy> getAllCopiesByMovie(Movie movie) throws CopiesNotFoundException {
        return findAllCopiesByMovie.getAllCopiesByMovie(movie);
    }

    @PutMapping("update")
    public Copy update(@RequestBody Copy copy) {
        return this.copyUpdate.update(copy);
    }
    @DeleteMapping("delete")
    public void delete(@PathVariable Copy copy) throws CopyNotExistException {
        this.copyDeleted.delete(copy.getId());
    }
}
