package com.fges.user.controller;

import com.fges.user.UserNotFoundException;
import com.fges.user.VO.ResponseTemplateVO;
import com.fges.user.entity.User;
import com.fges.user.event.RegistrationCompleteEvent;
import com.fges.user.model.UserModel;
import com.fges.user.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;
    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request) {
        log.info("----------REGISTER USER CONTROLLER");
        User user = userService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompleteEvent(
                user,
                applicationUrl(request)
        ));
        return "Success registration !";
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    @PostMapping
    public User saveUser(@RequestBody User user ){
        return userService.saveUser(user);
    }

    /*@GetMapping("/{userid}")
    public ResponseTemplateVO getUserWithBook(@PathVariable Long userId){
        log.info("Inside of getUserWithBook of UserController")
        return userService.getUserWithBook(userId);
    }*/
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


    @GetMapping("/userIds/{userIds}")
    public List<User> getUsersByIds(@PathVariable List<Long> userIds){
        return userService.getUsersByIds(userIds);
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

}
