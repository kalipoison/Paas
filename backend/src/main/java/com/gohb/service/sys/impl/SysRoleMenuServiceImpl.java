package com.gohb.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gohb.params.bo.sys.SysRoleMenuBO;
import com.gohb.service.sys.SysRoleMenuService;
import com.gohb.mapper.SysRoleMenuMapper;
import org.springframework.stereotype.Service;

/**
* @description 针对表【sys_role_menu(角色与菜单对应关系)】的数据库操作Service实现
*/
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenuBO>
    implements SysRoleMenuService{

}




