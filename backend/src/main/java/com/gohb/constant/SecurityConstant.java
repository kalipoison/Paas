package com.gohb.constant;

public interface SecurityConstant {

    /**
     * 请求头的 token 的 key
     */
    String AUTHORIZATION = "Authorization";

    /**
     * 请求头的 token 的前缀
     */
    String TOKEN_PREFIX = "Bearer ";

    /**
     * 登录的路径
     */
    String LOGIN_PATH = "/oauth/token";

}
