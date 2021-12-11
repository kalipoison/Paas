package com.gohb.manage.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gohb.bo.SysUserBO;
import com.gohb.convert.BoToDtoUtils;
import com.gohb.dto.SysMenuDTO;
import com.gohb.dto.SysUserDTO;
import com.gohb.manage.SysUserManage;
import com.gohb.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class SysUserManageImpl implements SysUserManage {

    @Autowired
    private SysUserService sysUserService;


    @Override
    public Boolean saveSysUser(SysUserBO sysUserBO) {
        boolean save = sysUserService.save(sysUserBO);
        return save;
    }

    @Override
    public Boolean deleteSysUser(Integer id) {
        boolean delete = sysUserService.removeById(id);
        return delete;
    }

    @Override
    public Boolean updateSysUser(SysUserBO sysUserBO) {
        boolean update = sysUserService.updateById(sysUserBO);
        return update;
    }

    @Override
    public List<SysUserDTO> listSysUser(SysUserBO sysUserBO) {
        List<SysUserBO> sysUserBOS = sysUserService.list(new LambdaQueryWrapper<SysUserBO>()
                .like(StringUtils.hasText(sysUserBO.getUsername()), SysUserBO::getUsername, sysUserBO.getUsername()));
        List<SysUserDTO> sysUserDTOS = new ArrayList<>();
        for (SysUserBO userBO : sysUserBOS) {
            sysUserDTOS.add(BoToDtoUtils.sysUserBOTOSysUserDTO(userBO));
        }
        return sysUserDTOS;
    }

    @Override
    public SysUserDTO getSysUserById(Integer id) {
        SysUserBO sysUserBO = sysUserService.getById(id);
        SysUserDTO sysUserDTO = BoToDtoUtils.sysUserBOTOSysUserDTO(sysUserBO);
        return sysUserDTO;
    }
}
