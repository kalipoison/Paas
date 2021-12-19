package com.gohb.manage.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gohb.bo.SysRoleBO;
import com.gohb.bo.SysUserBO;
import com.gohb.convert.BoToDtoUtils;
import com.gohb.dto.SysRoleDTO;
import com.gohb.manage.SysRoleManage;
import com.gohb.service.SysRoleService;
import com.gohb.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean
public class SysRoleManageImpl implements SysRoleManage {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserService sysUserService;

    @Override
    public Boolean saveRole(SysRoleBO sysRoleBO) {
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
        List<Long> createUserIds = sysRoleBOS.stream().map(SysRoleBO::getCreateUserId).collect(Collectors.toList());
        List<SysUserBO> sysUserBOS = sysUserService.listByIds(createUserIds);
        for (SysRoleBO roleBO : sysRoleBOS) {
            List<SysUserBO> userBOS = sysUserBOS.stream().filter(sysUserBO -> sysUserBO.getUserId().equals(roleBO.getCreateUserId())).collect(Collectors.toList());
            SysRoleDTO sysRoleDTO = BoToDtoUtils.sysRoleBOTOSysRoleDTO(roleBO);
            sysRoleDTO.setCreateUserName(userBOS.get(0).getUsername()); // 因为userid 唯一，所以取第一位
            sysRoleDTOS.add(sysRoleDTO);
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
