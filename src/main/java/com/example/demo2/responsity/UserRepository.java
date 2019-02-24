package com.example.demo2.responsity;

import com.example.demo2.bean.TUserEntity;

/**
 *
 * @author lihuaqing
 * @create 2019-02-21 18:09
 **/
public interface UserRepository extends StringBaseRepository<TUserEntity>{
    TUserEntity findByusername(String name);
    TUserEntity findByid(String userId);
    Integer deleteByusername(String name);
}