package com.fges.user.controller;

import com.fges.user.entity.User;
import com.fges.user.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RestController
@RequestMapping("/users")

public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User saveUser(@RequestBody User user ){
        //log.info("Dans saveUser de UserController");
        return userService.saveUser(user);
    }

    @GetMapping("/")
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/lastName/{userName}")
    public User getUserByName(@PathVariable String userName) throws Exception {
        return userService.getUserByName(userName);
    }

    @GetMapping("/id/{userId}")
    public User getUserById(@PathVariable Long userId) throws Exception {
        return userService.getUserById(userId);
    }
}
