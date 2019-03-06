package com.example.demo2.pojo;
/**
 * @author lihuaqing
 * @create 2019-03-05 15:37
 **/
public class Person1 {
    @Override
    public String toString() {
        return "Person1{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Person1() {
    }

    public Person1(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Integer id;
    private String name;
}