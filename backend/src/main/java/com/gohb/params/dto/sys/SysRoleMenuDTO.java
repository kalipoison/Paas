package com.gohb.params.dto.sys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 角色与菜单对应关系
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysRoleMenuDTO implements Serializable {

    /**
     *
     */
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

}
