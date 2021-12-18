package com.gohb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gohb.bo.SysUserBO;
import com.gohb.service.SysUserService;
import com.gohb.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
* @description 针对表【sys_user(用户)】的数据库操作Service实现
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserBO>
    implements SysUserService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean save(SysUserBO sysUserBO) {
        sysUserBO.setPassword(passwordEncoder.encode(sysUserBO.getPassword()));
        return super.save(sysUserBO);
    }
}




