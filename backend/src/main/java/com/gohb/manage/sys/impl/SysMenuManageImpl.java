package com.gohb.manage.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gohb.params.bo.sys.SysMenuBO;
import com.gohb.params.bo.sys.SysUserBO;
import com.gohb.convert.BoToDtoUtils;
import com.gohb.params.dto.sys.SysMenuDTO;
import com.gohb.manage.sys.SysMenuManage;
import com.gohb.service.sys.SysMenuService;
import com.gohb.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class SysMenuManageImpl implements SysMenuManage {

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysUserService sysUserService;

    @Override
    public Boolean saveMenu(SysMenuBO sysMenuBO) {
        boolean save = sysMenuService.save(sysMenuBO);
        return save;
    }

    @Override
    public Boolean deleteMenu(Integer id) {
        boolean delete = sysMenuService.removeById(id);
        return delete;
    }

    @Override
    public Boolean updateMenu(SysMenuBO sysMenuBO) {
        boolean update = sysMenuService.updateById(sysMenuBO);
        return update;
    }

    @Override
    public List<SysMenuDTO> listMenu(SysMenuBO sysMenuBO) {
        List<SysMenuBO> sysMenuBOS = sysMenuService.list();
        List<SysMenuDTO> sysMenuDTOS = new ArrayList<>();
        for (SysMenuBO menuBO : sysMenuBOS) {
            sysMenuDTOS.add(BoToDtoUtils.sysMenuBOTOSysMenuDTO(menuBO));
        }
        return sysMenuDTOS;
    }

    @Override
    public List<SysMenuDTO> loadUserMenu(String username) {
        SysUserBO sysUserBO = sysUserService.getOne(new LambdaQueryWrapper<SysUserBO>()
                .eq(SysUserBO::getUsername, username));
        List<SysMenuBO> sysMenuBOS = sysMenuService.loadUserMenu(sysUserBO.getUserId());
        List<SysMenuDTO> sysMenuDTOS = new ArrayList<>();
        for (SysMenuBO sysMenuBO : sysMenuBOS) {
            sysMenuDTOS.add(BoToDtoUtils.sysMenuBOTOSysMenuDTO(sysMenuBO));
        }
        return sysMenuDTOS;
    }
}
