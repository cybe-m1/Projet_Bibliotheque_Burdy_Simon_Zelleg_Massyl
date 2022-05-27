package com.fges.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//@EnableWebSecurity
public class WebSecurityConfig {

    private static final String[] WHITE_LIST_URLS = {
            "/hello",
            "http://USER-SERVICE/users/",
            "http://USER-SERVICE/users/register/",
            "http://localhost:9191/users/register/",
            "http://localhost:9191/users/",
            "/",
            "http://localhost:9002/login",
            "http://localhost:9002/users",
            "http://localhost:9002/users/register",
            "/hello",
            "/users",
            "users",
            "/register"
    };

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }

    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers("/**").permitAll();

        return http.build();
    }
}
