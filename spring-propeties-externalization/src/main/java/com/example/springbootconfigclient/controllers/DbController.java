/*
package com.example.springbootconfigclient.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DbController {
   private final ExternalProperties externalProperties;

   public DbController (ExternalProperties externalProperties) {
      this.externalProperties = externalProperties;
   }

   @GetMapping ("/db")
   private String greetingMessage(){
      return externalProperties.getConnection ()
              + externalProperties.getPass ()+externalProperties.getPort ();
   }
}
*/
