package com.example.moviecatalogfeignresilience.feignclient;



import com.example.moviecatalogfeignresilience.model.Rating;
import com.example.moviecatalogfeignresilience.model.UserRating;

import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.ws.rs.core.Response;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Optional;

@Component
//@FeignClient(name = "movie-rating",fallback = MovieRatingFeignFallback.class)
@FeignClient(name = "movie-rating")
public interface MovieRatingFeign {

   @GetMapping("/ratingsdata/user/{userId}")
   @Retry ( name = "raringInformation", fallbackMethod = "getRatingAndMovieName")
   UserRating getRatingAndMovieName (@PathVariable("userId") String userId);


   default UserRating getRatingAndMovieName (String userId, Exception exception) {

      System.out.println ("user rating fallback after retry: "+ exception.getCause ());
      //System.out.println (userId +" response status "+ response.status() +" request "+ response.request()+ " method "+ response.request().httpMethod());
      UserRating userRating = new UserRating ();
      userRating.setUserId ( userId );
      userRating.setRatings ( Arrays.asList (
              new Rating (exception.getCause ()+"Rating service problem for "+userId,Integer.MIN_VALUE)
      ));
      return userRating;
   }
}
