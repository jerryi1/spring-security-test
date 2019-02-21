package com.example.demo2.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lihuaqing
 * @create 2019-02-20 21:48
 **/
@RestController
@RequestMapping("/todos")
@PreAuthorize("hasRole('USER')")
public class TodoController {
    @RequestMapping(method = RequestMethod.GET)
    public String fortodo() {
        return "fortodo-success";
    }
}