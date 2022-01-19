package com.example.moviecatalogfeignresilience.feignclient;



import com.example.moviecatalogfeignresilience.model.Movie;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
//@FeignClient(name = "movie-info", fallback = MovieInfoFeignFallback.class)
@FeignClient(name = "movie-info")
public interface MovieInfoFeign {

   @GetMapping("/movies/{movieId}")
   //@Retry ( name = "movieinformation", fallbackMethod = "movieInfoFeignFallbackWithRetry")
   @CircuitBreaker ( name = "default", fallbackMethod = "movieInfoCircuitBreakerMethod")
   Movie getCatalogItemFein (@PathVariable("movieId") String movieId);

   default Movie movieInfoFeignFallbackWithRetry(Throwable throwable){
     System.out.println ("retry executed");
     return new Movie ("No Movie","MovieInfoServiceIssue");
   }

   default Movie movieInfoCircuitBreakerMethod(Throwable throwable){
     System.out.println ("Circuit breaker fallback method executed");
     return new Movie ("No Movie","MovieInfoServiceIssue");
   }
}


