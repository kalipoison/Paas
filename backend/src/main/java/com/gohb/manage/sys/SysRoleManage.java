package com.gohb.manage.sys;

import com.gohb.bo.sys.SysRoleBO;
import com.gohb.dto.SysRoleDTO;

import java.util.List;

public interface SysRoleManage {

    /**
     * 新增角色
     * @param sysRoleBO
     * @return
     */
    Boolean saveRole(SysRoleBO sysRoleBO);

    /**
     * 根据角色id 删除角色
     * @param id
     * @return
     */
    Boolean deleteRole(Integer id);

    /**
     * 更新角色信息
     * @param sysRoleBO
     * @return
     */
    Boolean updateRole(SysRoleBO sysRoleBO);

    /**
     * 查询所有角色信息，模糊查询
     * @return
     */
    List<SysRoleDTO> listRole(SysRoleBO sysRoleBO);

    /**
     * 查询角色详细信息
     * @return
     */
    SysRoleDTO getRoleById(Integer id);


}
