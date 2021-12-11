package com.gohb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gohb.bo.SysUserBO;
import com.gohb.service.SysUserService;
import com.gohb.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

/**
* @author zsh
* @description 针对表【sys_user(用户)】的数据库操作Service实现
* @createDate 2021-12-12 00:40:38
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserBO>
    implements SysUserService{

}




