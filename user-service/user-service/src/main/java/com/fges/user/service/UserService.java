package com.fges.user.service;

import com.fges.user.UserNotFoundException;
import com.fges.user.VO.Book;
import com.fges.user.VO.ResponseTemplateVO;
import com.fges.user.entity.User;
import com.fges.user.entity.VerificationToken;
import com.fges.user.model.UserModel;
import com.fges.user.repository.UserRepository;

import com.fges.user.repository.VerificationTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;
import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(UserModel userModel){
        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setName(userModel.getName());
        user.setAge(userModel.getAge());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));

        userRepository.save(user);
        return user;
    }

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

    public void saveVerificationTokenForUser(String token, User user) {
        VerificationToken verificationToken
                = new VerificationToken(user, token);
        //Save user and token to the database
        verificationTokenRepository.save(verificationToken);
    }


    public String validateVerificationToken(String token) {
        VerificationToken verificationToken =
                verificationTokenRepository.findByToken(token);
        if(verificationToken == null){
            return "invalid";
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();

        if((verificationToken.getExpirationTime().getTime()
        - cal.getTime().getTime()) <= 0){
            verificationTokenRepository.delete(verificationToken);
            return "expired";
        }

        user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }
}
