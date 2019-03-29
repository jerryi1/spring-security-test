package com.example.demo2.service.impl;

import com.example.demo2.bean.JwtUser;
import com.example.demo2.bean.TRoleEntity;
import com.example.demo2.bean.TUserEntity;
import com.example.demo2.responsity.UserRepository;
import com.example.demo2.service.AuthService;
import com.example.demo2.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author lihuaqing
 * @create 2019-02-20 16:15
 **/
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserRepository userRepository;


    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public AuthServiceImpl(
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    /**
     * 注册改版后的数据
     * */
    @Override
    public TUserEntity register(TUserEntity userToAdd) {
        // 初始化登记
        TRoleEntity tRoleEntity = new TRoleEntity();
        tRoleEntity.setId(1);
        tRoleEntity.setName("ROLE_NORMAL");
        Set<TRoleEntity> set = new HashSet();
        set.add(tRoleEntity);
        // 格式化密码
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String newpawd =  bCryptPasswordEncoder.encode(userToAdd.getPassword().trim());
        userToAdd.setPassword(newpawd);
        userToAdd.setRoles(set);
        TUserEntity backEntity = userRepository.save(userToAdd);
        return backEntity;
    }

    /**
     * 改版前的数据
     * 这个方法存在事务的问题，如果死在了一半怎么办？
     * */
//    @Override
//    public TUserEntity register(TUserEntity userToAdd) {
//        final String username = userToAdd.getUsername();
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        final String rawPassword = userToAdd.getPassword();
//        userToAdd.setPassword(encoder.encode(rawPassword));
//        /**
//         * 新增用户信息
//         * */
//        TUserEntity tUserEntity = userRepository.save(userToAdd);
//        if (tUserEntity==null){
//           log.info("添加用户失败"+tUserEntity);
//            try {
//                throw new Exception("添加用户失败");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        /**
//         * 添加用户角色的关系表
//         * 默认情况下的初始角色是   ROLE_NORMAL（可以写成静态常量）
//         * */
//        String userid =  tUserEntity.getId();
//        int defaultRoleIndex = RoleEnum.DEFAULT.getIndex();
//        UserRoleEntity userRoleEntity = new UserRoleEntity();
//        userRoleEntity.setUserId(userid);
//        userRoleEntity.setRoleId(defaultRoleIndex);
//        UserRoleEntity backUserRoleEntity= null;
//        try{
//            backUserRoleEntity = userRoleRepository.save(userRoleEntity);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        //添加默认的角色
//        TRoleEntity tRoleEntity = new TRoleEntity();
//        tRoleEntity.setName("ROLR_NORMAL");
//        Set<TRoleEntity> set = new HashSet<>();
//        set.add(tRoleEntity);
//        tUserEntity.setRoles(set);
//        return tUserEntity;
//    }





    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authenticate = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        System.out.println(userDetails);
        JwtUser userDetails = (JwtUser) authenticate.getPrincipal();
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    /**
     * 刷新token 的代码先注解掉
     * */
//    @Override
//    public String refresh(String oldToken) {
//        final String token = oldToken.substring(tokenHead.length());
//        String username = jwtTokenUtil.getUsernameFromToken(token);
//        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
//        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())){
//            return jwtTokenUtil.refreshToken(token);
//        }
//        return null;
//    }
}