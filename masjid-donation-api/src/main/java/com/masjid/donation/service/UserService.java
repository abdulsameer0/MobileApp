package com.masjid.donation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masjid.donation.entity.Status;
import com.masjid.donation.entity.User;
import com.masjid.donation.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String register(User user){
        user.setStatus(Status.PENDING);
        userRepository.save(user);
        return "Registration submitted, waiting for admin approval";
    }

    public List<User> getPendingUsers(){
        return userRepository.findAll()
                .stream()
                .filter(u -> u.getStatus() == Status.PENDING)
                .toList();
    }
    
    public List<User> getApproveUsers(){
        return userRepository.findAll()
                .stream()
                .filter(u -> u.getStatus() == Status.APPROVED)
                .toList();
    }
    
    public List<User> getRejectedUsers(){
        return userRepository.findAll()
                .stream()
                .filter(u -> u.getStatus() == Status.REJECTED)
                .toList();
    }


    public String approveUser(Long id){
        User user = userRepository.findById(id).orElseThrow();
        user.setStatus(Status.APPROVED);
        userRepository.save(user);
        return "User approved successfully";
    }

    public String rejectUser(Long id){
        User user = userRepository.findById(id).orElseThrow();
        user.setStatus(Status.REJECTED);
        userRepository.save(user);
        return "User rejected";
    }
    
    public String login(String username, String password){

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!user.getPassword().equals(password)){
            return "Invalid password";
        }

        if(user.getStatus() != Status.APPROVED){
            return "User not approved by admin";
        }

        return "Login successful";
    }

}

