package com.gohb.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gohb.mapper.client.MyUserMapper;
import com.gohb.params.bo.client.MyUserBO;
import com.gohb.params.bo.sys.SysUserBO;
import com.gohb.mapper.sys.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private MyUserMapper myUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ServletRequestAttributes requestAttributes =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String loginType = requestAttributes.getRequest().getHeader("loginType");

        if (loginType == null) {
            // 根据用户名拿到 用户
            SysUserBO sysUser = sysUserMapper.selectOne(new LambdaQueryWrapper<SysUserBO>()
                    .eq(SysUserBO::getUsername, username));
            if (!ObjectUtils.isEmpty(sysUser)) {
                List<String> auths = sysUserMapper.findUserAuthsById(sysUser.getUserId());
                if (!CollectionUtils.isEmpty(auths)) {
                    sysUser.setAuths(auths);
                }
            }
            return sysUser;
        } else if (loginType.equals("client")) {
            String email = requestAttributes.getRequest().getParameter("email");
            String password = requestAttributes.getRequest().getParameter("password");
            MyUserBO myUserBO = myUserMapper.selectOne(new LambdaQueryWrapper<MyUserBO>()
                    .eq(MyUserBO::getEmail, email));
            return myUserBO;
        } else {
            return null;
        }
    }
}
