package com.example.demo2.service;

import com.example.demo2.bean.User;
import com.example.demo2.factory.JwtUserFactory;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<String> list = new ArrayList();
        list.add("ROLE_ADMIN");
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 加密
//        String encodedPassword = passwordEncoder.encode("lhq032613".trim());
//        User user = new User("1","lhq",encodedPassword,"144886137@qq.com",new Date(),list);
        User user = new User("1","lhq","lhq032613","144886137@qq.com",new Date(),list);
        UserDetails userDetails = JwtUserFactory.create(user);
        return userDetails;
    }
}