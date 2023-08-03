package com.ismailulasunal.twitterclone;

import com.ismailulasunal.twitterclone.models.ApplicationUser;
import com.ismailulasunal.twitterclone.models.Role;
import com.ismailulasunal.twitterclone.repositories.RoleRepository;
import com.ismailulasunal.twitterclone.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TwitterCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwitterCloneApplication.class, args);
	}
}