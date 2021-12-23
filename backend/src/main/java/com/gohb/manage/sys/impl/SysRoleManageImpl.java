package com.gohb.manage.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gohb.bo.sys.SysRoleBO;
import com.gohb.convert.BoToDtoUtils;
import com.gohb.dto.sys.SysRoleDTO;
import com.gohb.exception.SysException;
import com.gohb.manage.sys.SysRoleManage;
import com.gohb.service.sys.SysRoleService;
import com.gohb.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean
public class SysRoleManageImpl implements SysRoleManage {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserService sysUserService;

    @Override
    public Boolean saveRole(SysRoleBO sysRoleBO) {
        SysRoleBO roleBO = sysRoleService.getOne(new LambdaQueryWrapper<SysRoleBO>()
                .eq(StringUtils.hasText(sysRoleBO.getRoleName()), SysRoleBO::getRoleName, sysRoleBO.getRoleName()));
        if (roleBO != null) {
            throw new SysException("用户名" + roleBO.getRoleName() + "已存在");
        }
        sysRoleBO.setCreateTime(new Date());
        boolean save = sysRoleService.save(sysRoleBO);
        return save;
    }

    @Override
    public Boolean deleteRole(Integer id) {
        boolean delete = sysRoleService.removeById(id);
        return delete;
    }

    @Override
    public Boolean updateRole(SysRoleBO sysRoleBO) {
        boolean update = sysRoleService.updateById(sysRoleBO);
        return update;
    }

    @Override
    public List<SysRoleDTO> listRole(SysRoleBO sysRoleBO) {
        List<SysRoleBO> sysRoleBOS = sysRoleService.list(new LambdaQueryWrapper<SysRoleBO>()
                .eq(StringUtils.hasText(sysRoleBO.getRoleName()), SysRoleBO::getRoleName, sysRoleBO.getRoleName()));
        List<SysRoleDTO> sysRoleDTOS = new ArrayList<>();
        for (SysRoleBO roleBO : sysRoleBOS) {
            sysRoleDTOS.add(BoToDtoUtils.sysRoleBOTOSysRoleDTO(roleBO));
        }
        return sysRoleDTOS;
    }

    @Override
    public SysRoleDTO getRoleById(Integer id) {
        SysRoleBO sysRoleBO = sysRoleService.getById(id);
        SysRoleDTO sysRoleDTO = BoToDtoUtils.sysRoleBOTOSysRoleDTO(sysRoleBO);
        return sysRoleDTO;
    }
}
