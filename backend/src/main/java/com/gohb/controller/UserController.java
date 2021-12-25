package com.gohb.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gohb.aop.anno.Log;
import com.gohb.params.bo.sys.SysUserBO;
import com.gohb.params.bo.sys.SysUserRoleBO;
import com.gohb.constant.StatusCodeConstant;
import com.gohb.params.dto.Result;
import com.gohb.params.dto.ResultUtils;
import com.gohb.params.dto.sys.SysUserDTO;
import com.gohb.manage.sys.SysUserManage;
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
     * http://localhost:8082/api/auth/user?username=admin1&password=admin&mobile=13888888888&email=admin@qq.com&status=1&type=0&userId=1
     * @param sysUserBO
     * @return
     */
    @PostMapping
    @Log(operation = "新增用户")
    public Result saveSysUser(SysUserBO sysUserBO) {
        // 拿到当前用户的id
//        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
//        sysUserBO.setCreateUserId(Long.valueOf(userId));
        Boolean save = sysUserManage.saveSysUser(sysUserBO);
        if (!save) {
            return ResultUtils.getFailedResult(StatusCodeConstant.createFail, "新增用户失败");
        }
        return ResultUtils.getSuccessResult("新增用户成功");
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @DeleteMapping()
    public Result delete(@RequestParam("id") Integer userId) {
        Boolean delete = sysUserManage.deleteSysUser(userId);
        if (!delete) {
            return ResultUtils.getFailedResult(StatusCodeConstant.deleteFail, "删除用户失败");
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
    public Result update(SysUserBO sysUserBO) {
        Boolean update = sysUserManage.updateSysUser(sysUserBO);
        if (!update) {
            return ResultUtils.getFailedResult(StatusCodeConstant.updateFail, "更新用户信息失败");
        }
        return ResultUtils.getSuccessResult("更新用户信息成功");
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("detail")
    @Log(operation = "查询用户详细信息")
    public Result<SysUserDTO> getSysUserDetail(@RequestParam("id") Integer userId) {
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
    public Result<List<SysUserDTO>> listSysUser(Page<SysUserBO> page, SysUserBO sysUser){
        IPage<SysUserDTO> sysUserDTOPage = sysUserManage.listSysUser(page, sysUser);
        return ResultUtils.getSuccessResult(sysUserDTOPage.getRecords()).setCount(sysUserDTOPage.getTotal());
    }


    /**
     * 保存用户角色对应关系
     * @param sysUserRoleBO
     * @return
     */
    @PostMapping("userRoles")
    public Result saveUserRole(SysUserRoleBO sysUserRoleBO) {
        Boolean save = sysUserManage.saveUserRole(sysUserRoleBO);
        if (!save) {
            return ResultUtils.getFailedResult(200,"更新用户的角色信息成功");
        }
        return ResultUtils.getSuccessResult("更新用户的角色信息成功");
    }

}
