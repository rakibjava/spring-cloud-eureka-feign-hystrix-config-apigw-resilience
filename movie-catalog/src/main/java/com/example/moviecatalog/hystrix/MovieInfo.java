package com.example.moviecatalog.hystrix;

import com.example.moviecatalog.model.CatalogItem;
import com.example.moviecatalog.model.Movie;
import com.example.moviecatalog.model.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class MovieInfo {

   final RestTemplate restTemplate;

   public MovieInfo (RestTemplate restTemplate, Environment environment) {
      this.restTemplate = restTemplate;
   }

   @HystrixCommand (fallbackMethod = "getFallbackCatalog",
           commandProperties = {
              @HystrixProperty ( name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
              @HystrixProperty ( name = "circuitBreaker.requestVolumeThreshold", value = "1"),
              @HystrixProperty ( name = "circuitBreaker.errorThresholdPercentage", value = "1"),
              @HystrixProperty ( name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")}
           /*,
           threadPoolKey = "movieThreadPool", threadPoolProperties = {
              @HystrixProperty ( name = "core.size", value = "20"),
              @HystrixProperty ( name = "maxQueueSize", value = "10")}*/
   )
   public CatalogItem getCatalogItem (Rating rating) {
      Movie movie = restTemplate.getForObject("http://movie-info/movies/" + rating.getMovieId(), Movie.class);
      return new CatalogItem(movie.getName(), "Description from: ", rating.getRating());
   }

   public CatalogItem getFallbackCatalog (Rating rating) {
      System.out.println ("no movie: ");
      return new CatalogItem ( "no movie found"+ rating.getMovieId (),"ERROR",rating.getRating ());
   }
}
