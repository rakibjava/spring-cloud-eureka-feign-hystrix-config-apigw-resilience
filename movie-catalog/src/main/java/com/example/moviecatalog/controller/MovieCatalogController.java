package com.example.moviecatalog.controller;

import com.example.moviecatalog.hystrix.MovieInfo;
import com.example.moviecatalog.hystrix.UserRatingInfo;
import com.example.moviecatalog.model.CatalogItem;
import com.example.moviecatalog.model.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

   final MovieInfo movieInfo;
   final UserRatingInfo userRatingInfo;

   public MovieCatalogController (MovieInfo movieInfo, UserRatingInfo userRatingInfo) {
      this.movieInfo = movieInfo;
      this.userRatingInfo = userRatingInfo;
   }

   @RequestMapping("/{userId}")
   public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

      UserRating userRating = userRatingInfo.getUserRating ( userId );

      return userRating.getRatings ()
              .stream()
              .map(rating -> movieInfo.getCatalogItem ( rating ))
              .collect(Collectors.toList());
   }
}
