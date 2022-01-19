package com.example.limitservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
public class LimitServiceApplication {

   public static void main (String[] args) {
      SpringApplication.run ( LimitServiceApplication.class, args );
   }

}
