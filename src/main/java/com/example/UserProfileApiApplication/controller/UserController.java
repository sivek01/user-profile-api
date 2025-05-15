package com.example.UserProfileApiApplication.controller;


import com.example.UserProfileApiApplication.entity.User;
import com.example.UserProfileApiApplication.service.AzureBlobService;
import com.example.UserProfileApiApplication.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AzureBlobService azureBlobService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PostMapping("/{id}/upload")
    public ResponseEntity<String> uploadImage(@PathVariable Long id, @RequestParam MultipartFile file) {
        String url = azureBlobService.upload(file);
        userService.updateImageUrl(id, url);
        return ResponseEntity.ok(url);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.of(userService.getUser(id));
    }
}
