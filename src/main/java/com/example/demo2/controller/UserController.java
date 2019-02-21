package com.example.demo2.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lihuaqing
 * @create 2019-02-20 15:43
 **/
@RestController
@RequestMapping("/users")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {
    @RequestMapping(method = RequestMethod.GET)
    public String getUsers() {
        return "search-success";
    }
}