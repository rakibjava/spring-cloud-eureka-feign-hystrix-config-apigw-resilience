package com.example.movieinfo.controller;

import com.example.movieinfo.MovieInfoApplication;
import com.example.movieinfo.model.Movie;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping ("/movies")
public class MovieInfoController {
    final Environment environment;

    public MovieInfoController (Environment environment) {
        this.environment = environment;
    }


    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable ("movieId") String movieId) {
        HashMap<String,String> map = MovieInfoApplication.setMovieData ();
        System.out.println ("Executing Movie Info from: "+environment.getProperty("local.server.port"));
        return new Movie(movieId, map.get ( movieId ));
    }

}