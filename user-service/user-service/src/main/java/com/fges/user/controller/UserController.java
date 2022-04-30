package com.fges.user.controller;

import com.fges.user.UserNotFoundException;
import com.fges.user.VO.ResponseTemplateVO;
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

    @PostMapping
    public User saveUser(@RequestBody User user ){
        return userService.saveUser(user);
    }

    @GetMapping
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

    @PutMapping
    public User update(@RequestBody User user) throws UserNotFoundException {
        return userService.updateUser(user);
    }

    @DeleteMapping(value="/delete/{userId}")
    public User delete(@PathVariable Long userId) throws Exception {
        User toDelete = userService.getUserById(userId);
        userService.deleteUserById(userId);
        return toDelete;
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithBooks(@PathVariable("id") Long userId){
        return  userService.getUserWithBooks(userId);
    }



}
