package com.example.demo2.responsity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 主键为String 的 Repository
 * @author lihuaqing
 * @create 2019-02-21 18:09
 **/
@NoRepositoryBean
public interface StringBaseRepository<T> extends JpaRepository<T,String>,
        JpaSpecificationExecutor<T>,
        QueryDslPredicateExecutor<T> {
}