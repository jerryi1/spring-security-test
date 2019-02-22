package com.example.demo2.service;

import org.springframework.stereotype.Component;

/**
 * @author lihuaqing
 * @create 2019-02-22 13:17
 **/
public interface UserService {
    Integer deleteUserByname(String name);
}