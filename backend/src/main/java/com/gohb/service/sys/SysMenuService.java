package com.gohb.service.sys;

import com.gohb.bo.sys.SysMenuBO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @description 针对表【sys_menu(菜单管理)】的数据库操作Service
*/
public interface SysMenuService extends IService<SysMenuBO> {

    /**
     * 加载菜单
     *
     * @param userId
     * @return
     */
    List<SysMenuBO> loadUserMenu(Long userId);

}
