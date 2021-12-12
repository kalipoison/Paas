package com.gohb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gohb.bo.SysMenuBO;
import com.gohb.service.SysMenuService;
import com.gohb.mapper.SysMenuMapper;
import org.springframework.stereotype.Service;

/**
* @description 针对表【sys_menu(菜单管理)】的数据库操作Service实现
*/
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuBO>
    implements SysMenuService{

}




