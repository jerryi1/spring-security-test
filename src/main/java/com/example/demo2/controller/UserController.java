package com.example.demo2.controller;

import com.example.demo2.archtechture.request.RestRequest;
import com.example.demo2.archtechture.response.RestResponse;
import com.example.demo2.bean.TUserEntity;
import com.example.demo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
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
     * 删除用户的时候，是否级联删除（会级联删除对应的用户)
     * */
    @PreAuthorize("hasRole('NORMAL')")
    @RequestMapping(method = RequestMethod.POST,value = "/delUserByName")
    public Integer delUserByName(String name) {
        return userService.deleteUserByname(name);
    }

    /**
     * 修改用户信息
     * 使用统一的返回类型 Restresponse
     * */
    @PreAuthorize("hasRole('NORMAL')")
    @RequestMapping(method = RequestMethod.POST,value = "/updateUserInfo")
    public RestResponse updateUserInfo(@RequestBody RestRequest<TUserEntity> restRequest) {
        TUserEntity tUserEntity = restRequest.getBody();
        System.out.println(tUserEntity+"修改的时候的用户信息");
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                String password = bCryptPasswordEncoder.encode(tUserEntity.getPassword());
        tUserEntity.setPassword(password);
        String updatemsg = userService.updateUserInfo(tUserEntity);
       return RestResponse.success().withMessage(updatemsg);
    }

    /**
     * 根据id 查找用户信息
     * */
    @PreAuthorize("hasRole('NORMAL')")
    @RequestMapping(method = RequestMethod.POST,value = "/finduserByid")
    public RestResponse finduserByid(String userId) {
        System.out.println("查找传入来的参数"+userId);
        return RestResponse.success(userService.findUserById(userId));
    }

}

