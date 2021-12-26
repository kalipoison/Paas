package com.gohb.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gohb.params.bo.sys.SysMenuBO;
import com.gohb.service.sys.SysMenuService;
import com.gohb.mapper.sys.SysMenuMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @description 针对表【sys_menu(菜单管理)】的数据库操作Service实现
*/
@Service
@Slf4j
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuBO>
    implements SysMenuService{

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenuBO> loadUserMenu(Long userId) {
        List<SysMenuBO> sysMenuBOS = sysMenuMapper.findMenuByUserId(userId);
        return loadMenuTree(sysMenuBOS, 0L);
    }

    @Override
    public List<SysMenuBO> list() {
        List<SysMenuBO> sysMenuBOS = sysMenuMapper.selectList(null);
        return loadMenuTree(sysMenuBOS, 0L);
    }

    /**
     * 组装树菜单
     * 使菜单具有层级结构
     * @param sysMenus 菜单
     * @param pid 父菜单id
     * @return
     */
    public List<SysMenuBO> loadMenuTree(List<SysMenuBO> sysMenus, Long pid){
        List<SysMenuBO> root = sysMenus.stream()
                .filter(sysMenu -> sysMenu.getParentId().equals(pid))
                .collect(Collectors.toList());
        root.stream().forEach(r->{
            List<SysMenuBO> child = sysMenus.stream()
                    .filter(sysMenu -> sysMenu.getParentId().equals(r.getMenuId())).collect(Collectors.toList());
            r.setList(child);
        });
        return root;
    }
}




