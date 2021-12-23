package com.gohb.controller;

import com.gohb.aop.anno.Log;
import com.gohb.bo.sys.SysRoleBO;
import com.gohb.constant.StatusCodeConstant;
import com.gohb.dto.Result;
import com.gohb.dto.ResultUtils;
import com.gohb.dto.sys.SysRoleDTO;
import com.gohb.manage.sys.SysRoleManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/role")
public class RoleController {

    @Autowired
    private SysRoleManage sysRoleManage;

    /**
     * 新增角色
     * @param sysRoleBO
     * @return
     */
    @PostMapping
    @Log(operation = "新增一个角色")
    public Result saveSysRole(SysRoleBO sysRoleBO) {
        Boolean save = sysRoleManage.saveRole(sysRoleBO);
        if (!save) {
            return ResultUtils.getFailedResult(StatusCodeConstant.createFail, "新增角色失败");
        }
        return ResultUtils.getSuccessResult("新增角色成功");
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @DeleteMapping()
    public Result deleteRoleById(Integer id) {
        Boolean delete = sysRoleManage.deleteRole(id);
        if (!delete) {
            return ResultUtils.getFailedResult(StatusCodeConstant.deleteFail, "删除角色失败");
        }
        return ResultUtils.getSuccessResult("删除角色成功");
    }

    /**
     * 修改角色信息
     * @param sysRoleBO
     * @return
     */
    @PutMapping
    @Log(operation = "修改角色信息")
    public Result updateSysRole(SysRoleBO sysRoleBO) {
        Boolean update = sysRoleManage.updateRole(sysRoleBO);
        if (!update) {
            return ResultUtils.getFailedResult(StatusCodeConstant.updateFail, "更新角色信息失败");
        }
        return ResultUtils.getSuccessResult("更新用户信息成功");
    }

    /**
     * 获取角色详细信息
     * @param roleId
     * @return
     */
    @GetMapping("detail")
    @Log(operation = "查询角色详细信息")
    public Result<SysRoleDTO> getSysRoleDetail(@RequestParam("id") Integer roleId) {
        SysRoleDTO sysRoleDTO = sysRoleManage.getRoleById(roleId);
        return ResultUtils.getSuccessResult(sysRoleDTO);
    }

    /**
     * 查询所有角色信息
     * @param sysRoleBO
     * @return
     */
    @GetMapping("")
    @Log(operation = "查询所有角色信息")
    public Result<List<SysRoleDTO>> listSysRole(SysRoleBO sysRoleBO){
        List<SysRoleDTO> sysRoleDTOS = sysRoleManage.listRole(sysRoleBO);
        return ResultUtils.getSuccessResult(sysRoleDTOS);
    }

}
