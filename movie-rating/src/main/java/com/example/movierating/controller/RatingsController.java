package com.example.movierating.controller;


import com.example.movierating.model.Rating;
import com.example.movierating.model.UserRating;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/ratingsdata")
public class RatingsController {

    @RequestMapping("/movies/{movieId}")
    public Rating getMovieRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);
    }

    @GetMapping ("/user/{userId}")
    public UserRating getUserRatings(@PathVariable("userId") String userId) {
        UserRating userRating = new UserRating();
        userRating.initData(userId);
        return userRating;
    }

}