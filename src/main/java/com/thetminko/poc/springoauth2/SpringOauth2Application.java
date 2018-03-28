package com.thetminko.poc.springoauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@SpringBootApplication
public class SpringOauth2Application {

	@RequestMapping("/user")
  @ResponseBody
  public Principal user(Principal user) {
	  return user;
  }

	public static void main(String[] args) {
		SpringApplication.run(SpringOauth2Application.class, args);
	}
}
