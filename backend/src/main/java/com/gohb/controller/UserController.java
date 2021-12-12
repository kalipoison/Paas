package com.gohb.controller;

import com.gohb.anno.Log;
import com.gohb.bo.SysUserBO;
import com.gohb.constant.STATUS_CODE;
import com.gohb.dto.Result;
import com.gohb.dto.ResultUtils;
import com.gohb.dto.SysUserDTO;
import com.gohb.manage.SysUserManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/user")
public class UserController {

    @Autowired
    private SysUserManage sysUserManage;


    /**
     * 新增用户
     * @param sysUserBO
     * @return
     */
    @PostMapping
    @Log(operation = "新增用户")
    public Result saveSysUser(@RequestBody SysUserBO sysUserBO) {
        Boolean save = sysUserManage.saveSysUser(sysUserBO);
        if (!save) {
            return ResultUtils.getFailedResult(STATUS_CODE.createFail, "新增用户失败");
        }
        return ResultUtils.getSuccessResult("新增用户成功");
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping()
    public Result delete(Integer id) {
        Boolean delete = sysUserManage.deleteSysUser(id);
        if (!delete) {
            return ResultUtils.getFailedResult(STATUS_CODE.deleteFail, "删除用户失败");
        }
        return ResultUtils.getSuccessResult("删除用户成功");
    }

    /**
     * 更新用户信息
     * @param sysUserBO
     * @return
     */
    @PutMapping
    @Log(operation = "更新用户信息")
    public Result update(@RequestBody SysUserBO sysUserBO) {
        Boolean update = sysUserManage.updateSysUser(sysUserBO);
        if (!update) {
            return ResultUtils.getFailedResult(STATUS_CODE.updateFail, "更新用户信息失败");
        }
        return ResultUtils.getSuccessResult("更新用户信息成功");
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("detail")
    @Log(operation = "查询用户详细信息")
    public Result<SysUserDTO> getSysUserDetail(Integer userId) {
        SysUserDTO sysUser = sysUserManage.getSysUserById(userId);
        return ResultUtils.getSuccessResult(sysUser);
    }

    /**
     * 查询所有用户信息
     * @param sysUser
     * @return
     */
    @GetMapping("")
    @Log(operation = "查询所有用户信息")
    public Result<List<SysUserDTO>> listSysUser(SysUserBO sysUser){
        List<SysUserDTO> sysUserDTOS = sysUserManage.listSysUser(sysUser);
        return ResultUtils.getSuccessResult(sysUserDTOS);
    }

}
