package com.example.demo2.responsity;

import com.example.demo2.bean.TUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 *
 * @author lihuaqing
 * @create 2019-02-21 18:09
 **/
public interface UserRepository extends JpaRepository<TUserEntity,String>, QueryDslPredicateExecutor<TUserEntity> {
    TUserEntity findByusername(String name);
    TUserEntity findByid(String userId);
    Integer deleteByusername(String name);
}