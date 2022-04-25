package com.fges.user.service;

import com.fges.user.entity.User;
import com.fges.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        //log.info("Dans saveUser de UserService");
        return userRepository.save(user);
    }
}
