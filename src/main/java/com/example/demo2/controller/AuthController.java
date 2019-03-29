package com.example.demo2.controller;

import com.example.demo2.bean.TUserEntity;
import com.example.demo2.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author lihuaqing
 * @create 2019-02-20 16:18
 **/
@RestController
@Slf4j
public class AuthController {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * token 获取
     * */
    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
        final String token = authService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        //以常量+username 作为key
//        redisTemplate.opsForValue().set(RedisConstant.LOGIN_TOKEN_REDIS_KEY+authenticationRequest.getUsername(),token,RedisConstant.LOGIN_TOKEN_REDIS_EXPIRE_TIME, TimeUnit.SECONDS);

        //获取一下认证一下是否成功
//        String value = redisTemplate.opsForValue().get(RedisConstant.LOGIN_TOKEN_REDIS_KEY);
//        log.info("redis 存储的信息为"+ value);
        // Return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }
    /**
     * 刷新token
     * */
//    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
//    public ResponseEntity<?> refreshAndGetAuthenticationToken(
//            HttpServletRequest request) throws AuthenticationException{
//        String token = request.getHeader(tokenHeader);
//        String refreshedToken = authService.refresh(token);
//        if(refreshedToken == null) {
//            return ResponseEntity.badRequest().body(null);
//        } else {
//            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
//        }
//    }
    /**
     * 用户的注册功能
     * */
    @RequestMapping(value = "${jwt.route.authentication.register}", method = RequestMethod.POST)
    public TUserEntity register(@RequestBody TUserEntity addedUser) throws AuthenticationException{
        return authService.register(addedUser);
    }

}