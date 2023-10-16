package com.pst.learncrew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.pst.learncrew")
public class ProfilesApplication {

  public static void main(String[] args) {
    SpringApplication.run(ProfilesApplication.class, args);
  }
}
