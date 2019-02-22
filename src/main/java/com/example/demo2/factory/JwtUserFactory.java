package com.example.demo2.factory;

import com.example.demo2.bean.JwtUser;

import java.util.List;

import com.example.demo2.bean.TRoleEntity;
import com.example.demo2.bean.TUserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.stream.Collectors;

/**
 * @author lihuaqing
 * @create 2019-02-20 15:32
 **/
public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(TUserEntity user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getRoles().stream().map(TRoleEntity::getName).collect(Collectors.toList()))
//                user.getLastPasswordResetDate()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}