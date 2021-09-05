package com.example.demo.controllers;

import com.example.demo.entities.Genre;
import com.example.demo.entities.Movie;
import com.example.demo.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/movieview")
public class MovieViewController {
    private final MovieRepository movieRepository;

    @GetMapping
    public String movieview(Model model,@RequestParam(name="genre", required=false) String genre){
        if(genre != null) {
            Genre genre1 = Genre.valueOf(genre);
            model.addAttribute("movies",movieRepository.findAllByGenre(genre1));
        } else{
        model.addAttribute("movies",movieRepository.findAll());}
        return "movieview";
    }


}
