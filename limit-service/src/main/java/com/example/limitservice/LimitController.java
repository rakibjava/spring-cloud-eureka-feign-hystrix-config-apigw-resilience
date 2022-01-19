package com.example.limitservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RefreshScope
public class LimitController {

   Environment env;

   public LimitController (Environment env) {
      this.env = env;
   }

   //@Value ( "${limit.service.minimum}" )
   @Value ( "#{'${limit.service.minimum}'}" )
   private String minValue;

   //@Value ( "${limit.service.maximum}" )
   @Value ( "#{'${limit.service.maximum}'}" )
   private String maxValue;

   @Value ( "#{'${limit.service.name}'}" )
   private String name;




   @GetMapping("/limit")
   public Limit getMaxMinVal() throws UnknownHostException {

      // Local address
      System.out.println (env.getProperty("server.port"));
      System.out.println (InetAddress.getLocalHost().getHostAddress());
      System.out.println (InetAddress.getLocalHost().getHostName());

      System.out.println (InetAddress.getLoopbackAddress().getHostAddress());;
      System.out.println (InetAddress.getLoopbackAddress().getHostName());;
      System.out.println (env.getProperty ( "limit.service.minimum" ) +"===and=="+ env.getProperty ( "limit.service.maximum" ) );
      return new Limit (name, minValue, maxValue );
   }
}
