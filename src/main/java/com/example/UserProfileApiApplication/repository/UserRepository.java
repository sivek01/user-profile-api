package com.example.UserProfileApiApplication.repository;


import com.example.UserProfileApiApplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}

