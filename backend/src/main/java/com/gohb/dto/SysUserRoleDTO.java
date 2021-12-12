package com.gohb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户与角色对应关系
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUserRoleDTO implements Serializable {

    /**
     *
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

}
