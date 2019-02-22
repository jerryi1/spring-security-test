package com.example.demo2.service.impl;

import com.example.demo2.responsity.UserRepository;
import com.example.demo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lihuaqing
 * @create 2019-02-22 13:19
 **/
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    @Transactional
    public Integer deleteUserByname(String name) {
        return userRepository.deleteByusername(name);
    }
}