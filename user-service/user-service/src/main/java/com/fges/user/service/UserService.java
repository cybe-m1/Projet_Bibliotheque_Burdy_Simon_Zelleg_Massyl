package com.fges.user.service;

import com.fges.user.UserNotFoundException;
import com.fges.user.entity.User;
import com.fges.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
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

    public List<User> getUsersByIds(List<Long> userIds){
        return userRepository.findByUserIdIn(userIds);
    }

    public User getUserById(Long userId) throws Exception{
        return userRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));
    }

    public User updateUser(User user) throws UserNotFoundException {
        if(userRepository.existsById(user.getUserId())) {
            return userRepository.save(user);
        }
        throw new UserNotFoundException("User does not exist ...");
    }

    public void deleteUserById(Long userId) throws UserNotFoundException {
        if(userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        } else {
            throw new UserNotFoundException("User does not exist ...");
        }
    }

}
