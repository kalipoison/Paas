package com.gohb.controller;

import com.gohb.aop.anno.Log;
import com.gohb.params.bo.sys.SysMenuBO;
import com.gohb.constant.StatusCodeConstant;
import com.gohb.params.dto.sys.MenuAndAuthDTO;
import com.gohb.params.dto.Result;
import com.gohb.params.dto.ResultUtils;
import com.gohb.params.dto.sys.SysMenuDTO;
import com.gohb.manage.sys.SysMenuManage;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/menu")
public class MenuController {

    @Autowired
    private SysMenuManage sysMenuManage;


    /**
     * 加载菜单和权限接口
     * @return
     */
    @GetMapping("nav")
    @ApiOperation(value = "加载用户菜单和权限的接口")
    @Log(operation = "加载用户菜单和权限的接口")
    public Result<MenuAndAuthDTO> loadMenuAndAuths(String username){
        List<SysMenuDTO> menus = sysMenuManage.loadUserMenu(username);
        MenuAndAuthDTO menuAndAuthDTO = new MenuAndAuthDTO(menus, null);
        return ResultUtils.getSuccessResult(menuAndAuthDTO);
    }


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
