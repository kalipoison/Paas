package com.gohb.manage.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gohb.bo.SysMenuBO;
import com.gohb.convert.BoToDtoUtils;
import com.gohb.dto.SysMenuDTO;
import com.gohb.manage.SysMenuManage;
import com.gohb.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class SysMenuManageImpl implements SysMenuManage {

    @Autowired
    private SysMenuService sysMenuService;

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
        List<SysMenuBO> sysMenuBOS = sysMenuService.list(new LambdaQueryWrapper<SysMenuBO>()
                .eq((sysMenuBO != null && !ObjectUtils.isEmpty(sysMenuBO.getType())), SysMenuBO::getType, sysMenuBO.getType()));
        List<SysMenuDTO> sysMenuDTOS = new ArrayList<>();
        for (SysMenuBO menuBO : sysMenuBOS) {
            sysMenuDTOS.add(BoToDtoUtils.sysMenuBOTOSysMenuDTO(menuBO));
        }
        return sysMenuDTOS;
    }
}
