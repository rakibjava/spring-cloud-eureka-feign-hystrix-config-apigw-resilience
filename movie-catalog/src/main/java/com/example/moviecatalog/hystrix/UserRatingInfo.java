package com.example.moviecatalog.hystrix;

import com.example.moviecatalog.model.Rating;
import com.example.moviecatalog.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserRatingInfo {

   final RestTemplate restTemplate;

   public UserRatingInfo (RestTemplate restTemplate, Environment environment) {
      this.restTemplate = restTemplate;
   }

   @HystrixCommand (
           fallbackMethod = "getFallbackUserRating",
           commandProperties = {
              @HystrixProperty ( name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
              @HystrixProperty ( name = "circuitBreaker.requestVolumeThreshold", value = "4"),
              @HystrixProperty ( name = "circuitBreaker.errorThresholdPercentage", value = "50"),
              @HystrixProperty ( name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
           }
           /*,
           threadPoolKey = "movieThreadPool", threadPoolProperties = {
              @HystrixProperty ( name = "core.size", value = "20"),
              @HystrixProperty ( name = "maxQueueSize", value = "10")
           }*/
   )

   public UserRating getUserRating (@PathVariable ("userId") String userId) {
      return restTemplate.getForObject("http://movie-rating/ratingsdata/user/" + userId, UserRating.class);
   }

   public UserRating getFallbackUserRating (@PathVariable("userId") String userId) {
      System.out.println ("no user rating==");
      UserRating userRating = new UserRating ();
      userRating.setUserId ( userId );
      userRating.setRatings ( Arrays.asList (
              new Rating ("Rating service problem for "+userId,Integer.MIN_VALUE)
      ));
      return userRating;
   }
}
