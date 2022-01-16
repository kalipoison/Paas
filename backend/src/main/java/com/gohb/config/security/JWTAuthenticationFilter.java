package com.gohb.config.security;

import com.alibaba.fastjson.JSON;
import com.gohb.params.bo.client.MyUserBO;
import com.gohb.params.bo.sys.SysUserBO;
import com.gohb.constant.SecurityConstant;
import com.gohb.constant.StatusCodeConstant;
import com.gohb.params.dto.Result;
import com.gohb.params.dto.ResultUtils;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证用户名密码正确后 生成一个token并将token返回给客户端
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager)
    {
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/api/login");
    }

    /**
     * 验证操作 接收并解析用户凭证
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,HttpServletResponse response) throws AuthenticationException {
        // 从输入流中获取到登录的信息
        String username = request.getHeader("loginType") == null ? request.getParameter("username") : request.getParameter("email");
        String password = request.getParameter("password");
        // 创建一个token并调用authenticationManager.authenticate() 让Spring security进行验证
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    /**
     * 验证【成功】后调用的方法
     * 若验证成功 生成token并返回
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,HttpServletResponse response,FilterChain chain,Authentication authResult) throws IOException {
        String username = "";
        String password = "";
        if (request.getHeader("loginType") == null) {
            SysUserBO sysUserBO= (SysUserBO) authResult.getPrincipal();
            username = sysUserBO.getUsername();
            password = sysUserBO.getPassword();
        } else {
            MyUserBO myUserBO = (MyUserBO) authResult.getPrincipal();
            username = myUserBO.getEmail();
            password = myUserBO.getPassword();
        }
        // 创建Token
        // 带有"Bearer "前缀的token字符串
        String token = SecurityConstant.TOKEN_PREFIX + JWTUtils.generateToken(username, password);
        // 设置编码 防止乱码问题
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        // 处理编码方式 防止中文乱码
        response.setContentType("text/json;charset=utf-8");
        // 将反馈塞到HttpServletResponse中返回给前台
        Result result = ResultUtils.getSuccessResult(token);
        response.getWriter().write(JSON.toJSONString(result));
    }

    /**
     * 验证【失败】调用的方法
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        String returnData="";
        // 账号过期
        if (failed instanceof AccountExpiredException) {
            returnData="账号过期";
        }
        // 密码错误
        else if (failed instanceof BadCredentialsException) {
            returnData="密码错误";
        }
        // 密码过期
        else if (failed instanceof CredentialsExpiredException) {
            returnData="密码过期";
        }
        // 账号不可用
        else if (failed instanceof DisabledException) {
            returnData="账号不可用";
        }
        //账号锁定
        else if (failed instanceof LockedException) {
            returnData="账号锁定";
        }
        // 用户不存在
        else if (failed instanceof InternalAuthenticationServiceException) {
            returnData="用户不存在";
        }
        // 其他错误
        else{
            returnData="未知异常";
        }

        // 处理编码方式 防止中文乱码
        response.setContentType("text/json;charset=utf-8");
        // 将反馈塞到HttpServletResponse中返回给前台
        Result result = ResultUtils.getFailedResult(StatusCodeConstant.loginFail, returnData);
        response.getWriter().write(JSON.toJSONString(result));
    }
}
