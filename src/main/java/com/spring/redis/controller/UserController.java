package com.spring.redis.controller;


import com.spring.redis.Repo.UserRepo;
import com.spring.redis.models.User;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepo userrepo;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        user.setUserId(UUID.randomUUID().toString());
       return this.userrepo.saveUser(user);
    }
    @GetMapping("/get/{userId}")
    public User createUser(@PathVariable String userId) {
        return this.userrepo.getUser(userId);
    }
    @GetMapping("/get/all")
    public ResponseEntity<?> getAllUser() {
        return this.userrepo.findAll();
    }
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
       return this.userrepo.deleteUser(userId);
    }
    @PutMapping ("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        return this.userrepo.updateUser(user);
    }
}
