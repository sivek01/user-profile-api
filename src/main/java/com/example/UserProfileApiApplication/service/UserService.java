package com.example.UserProfileApiApplication.service;


import com.example.UserProfileApiApplication.entity.User;
import com.example.UserProfileApiApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public Optional<User> getUser(Long id) {
        return userRepo.findById(id);
    }

    public void updateImageUrl(Long userId, String url) {
        User user = userRepo.findById(userId).orElseThrow();
        user.setImageUrl(url);
        userRepo.save(user);
    }
}
