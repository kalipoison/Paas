package com.gohb.controller;

import com.gohb.anno.Log;
import com.gohb.bo.SysMenuBO;
import com.gohb.constant.StatusCodeConstant;
import com.gohb.dto.Result;
import com.gohb.dto.ResultUtils;
import com.gohb.dto.SysMenuDTO;
import com.gohb.manage.SysMenuManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/menu")
public class MenuController {

    @Autowired
    private SysMenuManage sysMenuManage;

    /**
     * 新增菜单
     * @param sysMenuBO
     * @return
     */
    @PostMapping
    @Log(operation = "新增一个菜单")
    public Result saveSysMenu(@RequestBody SysMenuBO sysMenuBO) {
        Boolean save = sysMenuManage.saveMenu(sysMenuBO);
        if (!save) {
            return ResultUtils.getFailedResult(StatusCodeConstant.createFail, "新增菜单失败");
        }
        return ResultUtils.getSuccessResult("新增菜单成功");
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    @DeleteMapping()
    public Result deleteSysMenuById(Integer id) {
        Boolean delete = sysMenuManage.deleteMenu(id);
        if (!delete) {
            return ResultUtils.getFailedResult(StatusCodeConstant.deleteFail, "删除菜单失败");
        }
        return ResultUtils.getSuccessResult("删除菜单成功");
    }

    /**
     * 修改菜单
     * @param sysMenuBO
     * @return
     */
    @PutMapping
    @Log(operation = "修改菜单")
    public Result updateSysMenu(@RequestBody SysMenuBO sysMenuBO) {
        Boolean update = sysMenuManage.updateMenu(sysMenuBO);
        if (!update) {
            return ResultUtils.getFailedResult(StatusCodeConstant.updateFail, "更新菜单信息失败");
        }
        return ResultUtils.getSuccessResult("更新菜单信息成功");
    }

    /**
     * 查询所有菜单信息
     * @param sysMenuBO
     * @return
     */
    @GetMapping("")
    @Log(operation = "查询所有菜单信息")
    public Result<List<SysMenuDTO>> listSysUser(SysMenuBO sysMenuBO){
        List<SysMenuDTO> sysRoleDTOS = sysMenuManage.listMenu(sysMenuBO);
        return ResultUtils.getSuccessResult(sysRoleDTOS);
    }

}
