package com.example.demo2.service.impl;

import com.example.demo2.archtechture.response.RestResponse;
import com.example.demo2.bean.QTUserEntity;
import com.example.demo2.bean.TUserEntity;
import com.example.demo2.responsity.UserRepository;
import com.example.demo2.service.UserService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lihuaqing
 * @create 2019-02-22 13:19
 **/
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * 注入jpafactory
     * */
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    @Override
    @Transactional
    public Integer deleteUserByname(String name) {
        return userRepository.deleteByusername(name);
    }

    /**
     * 根据用户id 更新用户的信息
     * 存在的问题是，如果他要是改的话，是不是全部都要改掉了。如果我不想改变的话，怎么办？
     * */
    @Override
    @Transactional
    public String updateUserInfo(TUserEntity tUserEntity) {
        QTUserEntity qtUserEntity = QTUserEntity.tUserEntity;
//        Long updateNum = jpaQueryFactory.update(qtUserEntity).set(qtUserEntity.username,tUserEntity.getUsername())
//                                            .set(qtUserEntity.password,tUserEntity.getPassword())
//                                            .set(qtUserEntity.roles,tUserEntity.getRoles())
//                                            .where(qtUserEntity.id.eq(tUserEntity.getId()))
//                                            .execute();
//       return "更新成功";
        //如果我们执行save 操作的话
          userRepository.saveAndFlush(tUserEntity);


        //更新出线问题---》我们可以先删除再去新增实现修改
//        userRepository.delete(tUserEntity.getId());
//        userRepository.save(tUserEntity);
        return "delete-success";
    }

    @Override
    public RestResponse<TUserEntity> findUserById(String userId) {
        TUserEntity tUserEntity = userRepository.findByid(userId);
        System.out.println(tUserEntity+"查询返回的数据");
        return RestResponse.success(tUserEntity);
    }

}