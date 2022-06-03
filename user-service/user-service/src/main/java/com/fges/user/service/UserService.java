package com.fges.user.service;

import com.fges.user.UserNotFoundException;
import com.fges.user.entity.Role;
import com.fges.user.entity.User;
import com.fges.user.repository.RoleRepository;
import com.fges.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor @Transactional
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    public User saveUser(User user) {
        log.info("Saving new user {} to database", user.getUserName());
        return userRepository.save(user);
    }

    public Role saveRole(Role role){
        log.info("Saving new user {} to database", role.getName());
        return roleRepository.save(role);
    }

    public void addRoleToUser(String username, String roleName){
        log.info("Adding role {} to user {}", roleName, username);
        User user = userRepository.findByUserName(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    public List<User> getAll(){
        log.info("Get all users");
        return userRepository.findAll();
    }

    /*public User getUserByName(String name) throws Exception{
        return userRepository.findByName(name).orElseThrow(() -> new Exception("User not found"));
    }*/
    public User getUser(String username){
        log.info("Get user {}from database", username);
        return userRepository.findByUserName(username);
    }
    /*public List<User> getUsersByIds(List<Long> userIds){
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
    }*/

}
