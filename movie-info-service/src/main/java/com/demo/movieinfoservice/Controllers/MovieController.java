package com.demo.movieinfoservice.Controllers;

import com.demo.movieinfoservice.Models.Movie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @RequestMapping("/{movieId}")
    public Movie getMovie(@PathVariable String movieId){
        return new Movie("1","Diversent");
    }
}
