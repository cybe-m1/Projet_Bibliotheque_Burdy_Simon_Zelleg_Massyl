package com.fges.user;

import com.fges.user.entity.Role;
import com.fges.user.entity.User;
import com.fges.user.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;

@SpringBootApplication
@EnableEurekaClient
@EnableJpaRepositories
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null, "Role_USER"));
			userService.saveRole(new Role(null, "Role_MANAGER"));
			userService.saveRole(new Role(null, "Role_ADMIN"));
			userService.saveRole(new Role(null, "Role_SUPER_ADMIN"));

			userService.saveUser(new User(null, "Jhon", "Jhon@gmail.com", 22, "123", new ArrayList<>()));
			userService.saveUser(new User(null, "Will Smith", "WS@gmail.com", 28, "123", new ArrayList<>()));
			userService.saveUser(new User(null, "JC", "JC@gmail.com", 25, "123", new ArrayList<>()));
			userService.saveUser(new User(null, "Arnold", "Arnold@gmail.com", 22, "123", new ArrayList<>()));

			userService.addRoleToUser("Jhon", "ROLE_USER");
			userService.addRoleToUser("Jhon", "ROLE_MANAGER");
			userService.addRoleToUser("Will Smith", "ROLE_MANAGER");
			userService.addRoleToUser("JC", "ROLE_ADMIN");
			userService.addRoleToUser("Arnold", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("Arnold", "ROLE_ADMIN");
			userService.addRoleToUser("Arnold", "ROLE_USER");
		};
	}

}
