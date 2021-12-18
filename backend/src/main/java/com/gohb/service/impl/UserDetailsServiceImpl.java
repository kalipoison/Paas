package com.gohb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gohb.bo.SysUserBO;
import com.gohb.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
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
    }
}
