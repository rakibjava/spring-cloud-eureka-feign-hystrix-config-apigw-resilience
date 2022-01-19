package com.example.moviecatalogfeignresilience.feignclient;


import com.example.moviecatalogfeignresilience.model.Rating;
import com.example.moviecatalogfeignresilience.model.UserRating;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MovieRatingFeignFallback implements MovieRatingFeign{
   @Override
   public UserRating getRatingAndMovieName (String userId) {
      System.out.println ("no user rating==");
      UserRating userRating = new UserRating ();
      userRating.setUserId ( userId );
      userRating.setRatings ( Arrays.asList (
              new Rating ("Rating service problem for "+userId,Integer.MIN_VALUE)
      ));
      return userRating;
   }
}
