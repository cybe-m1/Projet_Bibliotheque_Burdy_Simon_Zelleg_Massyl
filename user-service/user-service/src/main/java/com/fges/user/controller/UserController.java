package com.fges.user.controller;

import com.fges.user.UserNotFoundException;
import com.fges.user.VO.ResponseTemplateVO;
import com.fges.user.entity.Role;
import com.fges.user.entity.User;
import com.fges.user.service.UserService;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /*@PostMapping
    public User saveUser(@RequestBody User user ){
        return userService.saveUser(user);
    }*/

    /*@GetMapping("/{userid}")
    public ResponseTemplateVO getUserWithBook(@PathVariable Long userId){
        log.info("Inside of getUserWithBook of UserController")
        return userService.getUserWithBook(userId);
    }*/
    /*@GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }*/

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getAll());
    }

    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveUser(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addTouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){
        userService.addRoleToUser(form.getUsername(), form.getRolename());
        return ResponseEntity.ok().build();

    }



    /*@GetMapping("/lastName/{userName}")
    public User getUserByName(@PathVariable String userName) throws Exception {
        return userService.getUserByName(userName);
    }*/

    /*@GetMapping("/id/{userId}")
    public User getUserById(@PathVariable Long userId) throws Exception {
        return userService.getUserById(userId);
    }*/


    /*@GetMapping("/userIds/{userIds}")
    public List<User> getUsersByIds(@PathVariable List<Long> userIds){
        return userService.getUsersByIds(userIds);
    }

    @PutMapping
    public User update(@RequestBody User user) throws UserNotFoundException {
        return userService.updateUser(user);
    }*/

    /*@DeleteMapping(value="/delete/{userId}")
    public User delete(@PathVariable Long userId) throws Exception {
        User toDelete = userService.getUserById(userId);
        userService.deleteUserById(userId);
        return toDelete;
    }*/

}

@Data
class RoleToUserForm {
    private String username;
    private String rolename;
}
