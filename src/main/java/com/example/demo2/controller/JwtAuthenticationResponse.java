package com.example.demo2.controller;

import java.io.Serializable;

/**
 * @author lihuaqing
 * @create 2019-02-20 16:24
 **/

public class JwtAuthenticationResponse implements Serializable {
    private static final long serialVersionUID = 4784951536404964122L;
    private final String token;   //要发送回客户端的令牌

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}