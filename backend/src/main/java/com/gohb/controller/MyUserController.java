package com.gohb.controller;

import com.gohb.aop.anno.Log;
import com.gohb.constant.StatusCodeConstant;
import com.gohb.manage.Client.MyUserManage;
import com.gohb.params.bo.client.MyUserBO;
import com.gohb.params.bo.notify.NotifyBO;
import com.gohb.params.dto.Result;
import com.gohb.params.dto.ResultUtils;
import com.gohb.params.dto.client.MyUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/myUser")
public class MyUserController {

    @Autowired
    private MyUserManage myUserManage;


    @PostMapping("")
    public Result saveMyUser(MyUserBO myUserBO) {
        Boolean register = myUserManage.saveMyUser(myUserBO);
        if (!register) {
            return ResultUtils.getFailedResult(StatusCodeConstant.createFail, "注册失败");
        }
        return ResultUtils.getSuccessResult("注册成功");
    }

    /**
     * 删除 myUser
     * @param id
     * @return
     */
    @DeleteMapping()
    public Result deleteMyUserById(Integer id) {
        Boolean delete = myUserManage.deleteMyUser(id);
        if (!delete) {
            return ResultUtils.getFailedResult(StatusCodeConstant.deleteFail, "删除 myUser 失败");
        }
        return ResultUtils.getSuccessResult("删除 myUser 成功");
    }

    /**
     * 修改 myUser 信息
     * @param myUserBO
     * @return
     */
    @PutMapping
    @Log(operation = "修改 myUser 信息")
    public Result updateMyUser(MyUserBO myUserBO) {
        Boolean update = myUserManage.updateMyUser(myUserBO);
        if (!update) {
            return ResultUtils.getFailedResult(StatusCodeConstant.updateFail, "更新 myUser 信息失败");
        }
        return ResultUtils.getSuccessResult("更新 myUser 信息成功");
    }

    /**
     * 获取 myUserBO 列表
     * @param myUserBO
     * @return
     */
    @GetMapping("")
    @Log(operation = "查询 myUser 列表")
    public Result<List<MyUserDTO>> getMyUserList(MyUserBO myUserBO) {
        List<MyUserDTO> myUserDTOS = myUserManage.listMyUser(myUserBO);
        return ResultUtils.getSuccessResult(myUserDTOS).setCount(Long.valueOf(myUserDTOS.size()));
    }

    /**
     * 获取 myUser 详情
     * @param id
     * @return
     */
    @GetMapping("/detail")
    @Log(operation = "查询 myUser 详细信息")
    public Result<MyUserDTO> getMyUserDetail(Integer id) {
        MyUserDTO myUserDTO = myUserManage.detailMyUser(id);
        return ResultUtils.getSuccessResult(myUserDTO);
    }


}
