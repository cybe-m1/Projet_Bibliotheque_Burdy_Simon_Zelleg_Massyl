package com.fges.user.service;

import com.fges.user.entity.User;
import com.fges.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        //log.info("Dans saveUser de UserService");
        return userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getUserByName(String name) throws Exception{
        return userRepository.findByName(name).orElseThrow(() -> new Exception("User not found"));
    }

    public User getUserById(Long id) throws Exception{
        return userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
    }

    //public User updateUser(User user){

    //}

}
