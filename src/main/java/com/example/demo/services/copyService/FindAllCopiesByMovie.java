package com.example.demo.services.copyService;

import com.example.demo.entities.Copy;
import com.example.demo.entities.Movie;
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
public class FindAllCopiesByMovie {

    private CopyRepository copyRepository;

    public List<Copy> getAllCopiesByMovie(Movie movie) throws CopiesNotFoundException {
        List<Copy>copiesByMovie=new ArrayList<>();
        copyRepository.findAllByMovie(movie).forEach(copiesByMovie::contains);
        if(copiesByMovie.isEmpty()){
            throw new CopiesNotFoundException("This movie"+ movie +"HAS NOT COPIES!");
        }
        return copiesByMovie;

    }
}
