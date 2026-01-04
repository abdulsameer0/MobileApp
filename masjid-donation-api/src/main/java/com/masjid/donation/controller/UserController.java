package com.masjid.donation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masjid.donation.dto.LoginRequest;
import com.masjid.donation.entity.User;
import com.masjid.donation.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody User user){
        return userService.register(user);
    }

    @GetMapping("/pending")
    public List<User> pendingUsers(){
        return userService.getPendingUsers();
    }
    
    @GetMapping("/approved")
    public List<User> approvedUsers(){
        return userService.getApproveUsers();
    }
    
    @GetMapping("/rejected")
    public List<User> rejectUsers(){
        return userService.getRejectedUsers();
    }

    @PutMapping("/approve/{id}")
    public String approve(@PathVariable Long id){
        return userService.approveUser(id);
    }

    @PutMapping("/reject/{id}")
    public String reject(@PathVariable Long id){
        return userService.rejectUser(id);
    }
    
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){
        return userService.login(request.getUsername(), request.getPassword());
    }
}

