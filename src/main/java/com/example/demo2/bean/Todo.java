package com.example.demo2.bean;

import lombok.Data;

import javax.persistence.Id;

/**
 * @author lihuaqing
 * @create 2019-02-20 15:31
 **/
@Data
public class Todo {
    @Id
    private String id;
    private String desc;
    private boolean completed;
    private User user;
}