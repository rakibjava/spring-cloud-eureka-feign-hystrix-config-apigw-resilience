package com.example.movieinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableEurekaClient
public class MovieInfoApplication {

   public static void main (String[] args) {
      SpringApplication.run ( MovieInfoApplication.class, args );
   }


   public static HashMap<String,String> setMovieData(){
      HashMap<String,String> map = new HashMap<> ();
      map.put ( "1234","SpiderMan" );
      map.put ( "5678","Transformer" );
      return map;
   }
}
