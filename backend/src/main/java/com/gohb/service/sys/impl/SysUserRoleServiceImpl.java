package com.gohb.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gohb.bo.sys.SysUserRoleBO;
import com.gohb.service.sys.SysUserRoleService;
import com.gohb.mapper.SysUserRoleMapper;
import org.springframework.stereotype.Service;

/**
* @description 针对表【sys_user_role(用户与角色对应关系)】的数据库操作Service实现
*/
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRoleBO>
    implements SysUserRoleService{

}




