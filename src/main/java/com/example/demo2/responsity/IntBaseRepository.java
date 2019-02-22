package com.example.demo2.responsity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 主键为Integer 的 Repository
 * @author lihuaqing
 * @create 2019-02-22 12:19
 **/
@NoRepositoryBean
public interface IntBaseRepository<T> extends JpaRepository<T,Integer>,
        JpaSpecificationExecutor<T>,
        QueryDslPredicateExecutor<T> {
}
