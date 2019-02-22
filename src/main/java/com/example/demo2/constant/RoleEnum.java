package com.example.demo2.constant;

/**
 * @author lihuaqing
 * @create 2019-02-22 11:35
 **/
public enum RoleEnum {
    DEFAULT("ROLR_NORMAL", 1), GREEN("ROLR_ADMIN", 2);
    // 成员变量
    private String name;
    private int index;

    public static String getName(int index) {
        for(RoleEnum role : RoleEnum.values()){
            if (role.getIndex()==index){
                return role.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    RoleEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }}