package com.example.demo2.controller;

import com.example.demo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * 查找所有用户的时候，验证权限是否符合
     * */

    @PreAuthorize("hasRole('NORMAL')")
    @RequestMapping(method = RequestMethod.GET,value = "/searchUsers")
    public String getUsers() {
        return "search-success";
    }

    /**
     * 删除用户的时候，是否级联删除
     * */
    @PreAuthorize("hasRole('NORMAL')")
    @RequestMapping(method = RequestMethod.POST,value = "/delUserByName")
    public Integer delUserByName(String name) {
        return userService.deleteUserByname(name);
    }
}

