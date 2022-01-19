package com.example.moviecatalogfeignresilience.feignclient;

import com.example.moviecatalogfeignresilience.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieInfoFeignFallback implements MovieInfoFeign{
   @Override
   public Movie getCatalogItemFein (String movieId) {
      System.out.println ("no movie rating==");
      return new Movie (movieId,"MovieInfoServiceIssue");
   }
}
