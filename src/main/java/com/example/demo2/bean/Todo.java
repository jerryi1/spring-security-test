package com.example.demo2.bean;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author lihuaqing
 * @create 2019-02-20 15:31
 **/
@Data
public class Todo implements Serializable {
    @Id
    private String id;
    private String desc;
    private boolean completed;
}