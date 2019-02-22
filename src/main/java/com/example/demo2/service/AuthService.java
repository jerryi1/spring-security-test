package com.example.demo2.service;

import com.example.demo2.bean.TUserEntity;

public interface AuthService {
    TUserEntity register(TUserEntity userToAdd);
    String login(String username, String password);
//    String refresh(String oldToken);
}