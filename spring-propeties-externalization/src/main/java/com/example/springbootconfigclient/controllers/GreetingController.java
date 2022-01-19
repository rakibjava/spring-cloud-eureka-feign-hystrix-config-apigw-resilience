package com.example.springbootconfigclient.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

   @Value ( "${my.greeting}" )
   private String greet;

   @Value ( "${my.application.name}" )
   private String applicationName;

   @Value ( "${db.connection}" )
   private String connection;
   @Value ( "${db.port}" )
   private int port;
   @Value ( "${db.pass}" )
   private String pass;

   @GetMapping("/greeting")
   private String greetingMessage(){
      return greet + "  "+applicationName+ connection + pass+port;
   }
}
