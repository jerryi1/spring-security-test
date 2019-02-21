package com.example.demo2.service;

import com.example.demo2.bean.User;

public interface AuthService {
//    User register(User userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}