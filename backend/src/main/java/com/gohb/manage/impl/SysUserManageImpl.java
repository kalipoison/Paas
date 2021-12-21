package com.gohb.manage.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gohb.bo.SysRoleBO;
import com.gohb.bo.SysUserBO;
import com.gohb.bo.SysUserRoleBO;
import com.gohb.convert.BoToDtoUtils;
import com.gohb.dto.SysMenuDTO;
import com.gohb.dto.SysUserDTO;
import com.gohb.manage.SysUserManage;
import com.gohb.service.SysRoleService;
import com.gohb.service.SysUserRoleService;
import com.gohb.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean
public class SysUserManageImpl implements SysUserManage {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Boolean saveSysUser(SysUserBO sysUserBO) {
        sysUserBO.setStatus(1); // 新建用户为正常状态
        sysUserBO.setCreateTime(new Date());
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
        // 为空，认为不修改密码
        // 否则BCRY加密
        if (sysUserBO.getPassword().equals("")) {
            sysUserBO.setPassword(null);
        } else {
            sysUserBO.setPassword(passwordEncoder.encode(sysUserBO.getPassword()));
        }
        boolean update = sysUserService.updateById(sysUserBO);
        return update;
    }

    @Override
    public IPage<SysUserDTO> listSysUser(Page<SysUserBO> page, SysUserBO sysUserBO) {
        // 分页查询出所有用户
        Page<SysUserBO> sysUserBOPage = sysUserService.page(page,new LambdaQueryWrapper<SysUserBO>()
                .like(StringUtils.hasText(sysUserBO.getUsername()), SysUserBO::getUsername, sysUserBO.getUsername()));
        List<SysUserBO> sysUserBOS = sysUserBOPage.getRecords();
        List<Long> sysUserIds = sysUserBOS.stream().map(SysUserBO::getUserId).collect(Collectors.toList());
        // 查询所有userRole
        List<SysUserRoleBO> sysUserRoleBOS = sysUserRoleService.listByIds(sysUserIds);
        List<Long> sysUserRoleIds = sysUserRoleBOS.stream().map(SysUserRoleBO::getRoleId).collect(Collectors.toList());
        // 查询所有role
        List<SysRoleBO> sysRoleBOS = sysRoleService.listByIds(sysUserRoleIds);
        // 组装数据
        List<SysUserDTO> sysUserDTOS = new ArrayList<>();
        sysUserBOS.forEach(userBO -> {
            SysUserDTO sysUserDTO = BoToDtoUtils.sysUserBOTOSysUserDTO(userBO);
            List<SysUserRoleBO> userRoleBOS = sysUserRoleBOS.stream().filter(sysUserRoleBO -> sysUserRoleBO.getUserId().equals(userBO.getUserId())).collect(Collectors.toList());
            StringBuilder roleName = new StringBuilder();
            userRoleBOS.forEach(userRoleBO -> {
                List<SysRoleBO> roleBOS = sysRoleBOS.stream().filter(roleBO -> roleBO.getRoleId().equals(userRoleBO.getRoleId())).collect(Collectors.toList());
                roleBOS.forEach(roleBO -> roleName.append(roleBO.getRoleName()+";"));
            });
            sysUserDTO.setRoleName(roleName.toString());
            sysUserDTOS.add(sysUserDTO);
        });
        IPage<SysUserDTO> sysUserDTOPage = new Page<>();
        sysUserDTOPage.setTotal(sysUserBOPage.getTotal());
        sysUserDTOPage.setRecords(sysUserDTOS);
        return sysUserDTOPage;
    }

    @Override
    public SysUserDTO getSysUserById(Integer id) {
        SysUserBO sysUserBO = sysUserService.getById(id);
        SysUserDTO sysUserDTO = BoToDtoUtils.sysUserBOTOSysUserDTO(sysUserBO);
        return sysUserDTO;
    }

    @Override
    public Boolean saveUserRole(SysUserRoleBO sysUserRoleBO) {
        boolean save = sysUserRoleService.save(sysUserRoleBO);
        return save;
    }

}
