package com.example.springcloudconfigservergithub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SpringCloudConfigServerGithubApplication {

   public static void main (String[] args) {
      SpringApplication.run ( SpringCloudConfigServerGithubApplication.class, args );
   }

}
