package com.userservice.userservice;

import com.userservice.userservice.domain.Role;
import com.userservice.userservice.domain.User;
import com.userservice.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null, "Role_USER"));
			userService.saveRole(new Role(null, "Role_MANAGER"));
			userService.saveRole(new Role(null, "Role_ADMIN"));
			userService.saveRole(new Role(null, "Role_SUPER_ADMIN"));

			userService.saveUser(new User(null, "Jhon Travolta", "jhon", "Jhon@gmail.com", 22, "123", new ArrayList<>()));
			userService.saveUser(new User(null, "Will Smith", "will", "WS@gmail.com", 28, "123", new ArrayList<>()));
			userService.saveUser(new User(null, "Jim Carry", "JC", "JC@gmail.com", 25, "123", new ArrayList<>()));
			userService.saveUser(new User(null, "Arnold Shwarzy", "arnold", "Arnold@gmail.com", 22, "123", new ArrayList<>()));

			userService.addRoleToUser("jhon", "ROLE_USER");
			userService.addRoleToUser("jhon", "ROLE_MANAGER");
			userService.addRoleToUser("will", "ROLE_MANAGER");
			userService.addRoleToUser("JC", "ROLE_ADMIN");
			userService.addRoleToUser("arnold", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("arnold", "ROLE_ADMIN");
			userService.addRoleToUser("arnold", "ROLE_USER");
		};
	}

}
