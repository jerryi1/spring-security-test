package com.example.demo2.service;

import com.example.demo2.archtechture.response.RestResponse;
import com.example.demo2.bean.TUserEntity;
import org.springframework.stereotype.Component;

/**
 * @author lihuaqing
 * @create 2019-02-22 13:17
 **/
public interface UserService {
    /**
     * 根据用户名字删除用户信息
     * */
    Integer deleteUserByname(String name);

    /**
     * 更新用户信息
     * */
    String updateUserInfo(TUserEntity tUserEntity);

    /**
     * 根据id 查找用户信息
     * */
    RestResponse findUserById(String userId);

}