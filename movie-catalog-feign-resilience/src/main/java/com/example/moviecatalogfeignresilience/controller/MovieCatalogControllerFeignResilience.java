package com.example.moviecatalogfeignresilience.controller;



import com.example.moviecatalogfeignresilience.feignclient.MovieInfoFeign;
import com.example.moviecatalogfeignresilience.feignclient.MovieRatingFeign;
import com.example.moviecatalogfeignresilience.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping ("/feign/resilience")
public class MovieCatalogControllerFeignResilience {

   private final MovieRatingFeign movieRatingFeign;
   private final MovieInfoFeign movieInfoFeign;

   @Autowired
   public MovieCatalogControllerFeignResilience (MovieRatingFeign movieRatingFeign, MovieInfoFeign movieInfoFeign) {
      this.movieRatingFeign = movieRatingFeign;
      this.movieInfoFeign = movieInfoFeign;
   }

   @GetMapping ("/catalog/{userId}")
   public UserMovieCatalogInfo getMovieInfo(@PathVariable ("userId") String userId) {

      UserRating userRating = movieRatingFeign.getRatingAndMovieName (userId);

      List<CatalogItem> movieCatalog = new ArrayList<> ();

      for ( Rating rate: userRating.getRatings () ){
         Movie mInfo =  movieInfoFeign.getCatalogItemFein ( rate.getMovieId () );
         CatalogItem catalogItem =  new CatalogItem ( mInfo.getName (),mInfo.getName()+"Description from: ", rate.getRating ());
         movieCatalog.add ( catalogItem );
      }

      return new UserMovieCatalogInfo (userId,movieCatalog);

   }

}
