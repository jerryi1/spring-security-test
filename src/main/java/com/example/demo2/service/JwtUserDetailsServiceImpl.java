package com.example.demo2.service;

import com.example.demo2.bean.JwtUser;
import com.example.demo2.bean.TUserEntity;
import com.example.demo2.factory.JwtUserFactory;
import com.example.demo2.responsity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * @author lihuaqing
 * @create 2019-02-20 15:35
 **/
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        List<String> list = new ArrayList();
//        list.add("ROLE_ADMIN");
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        // 加密
//        String encodedPassword = passwordEncoder.encode("lhq032613".trim());
//        TUserEntity user = new TUserEntity("1","lhq",encodedPassword,"144886137@qq.com",new Date(),list);
////        User user = new User("1","lhq","lhq032613","144886137@qq.com",new Date(),list);
//        UserDetails userDetails =
        TUserEntity tUserEntity = userRepository.findByusername(username);
        System.out.println(tUserEntity+"數據庫查詢出來的結果是："+tUserEntity);
        JwtUser jwtUser = JwtUserFactory.create(tUserEntity);
        System.out.println(jwtUser+"返回之後的結果");
        return jwtUser;
    }
}