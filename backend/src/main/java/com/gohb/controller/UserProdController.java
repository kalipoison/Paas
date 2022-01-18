package com.gohb.controller;

import com.gohb.aop.anno.Log;
import com.gohb.constant.StatusCodeConstant;
import com.gohb.manage.Client.UserProdManage;
import com.gohb.params.bo.client.UserProdBO;
import com.gohb.params.dto.Result;
import com.gohb.params.dto.ResultUtils;
import com.gohb.params.dto.client.UserProdDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/userProd")
public class UserProdController {

    @Autowired
    private UserProdManage userProdManage;

    /**
     * 新增 userProd
     * @param userProdBO
     * @return
     */
    @PostMapping
    public Result saveUserProd(UserProdBO userProdBO) {
        Boolean save = userProdManage.saveUserProd(userProdBO);
        if (!save) {
            return ResultUtils.getFailedResult(StatusCodeConstant.createFail, "新增 用户产品 失败");
        }
        return ResultUtils.getSuccessResult("新增 用户产品 成功");
    }

    /**
     * 删除 userProd
     * @param id
     * @return
     */
    @DeleteMapping()
    public Result deleteUserProdById(Integer id) {
        Boolean delete = userProdManage.deleteUserProd(id);
        if (!delete) {
            return ResultUtils.getFailedResult(StatusCodeConstant.deleteFail, "删除 用户产品 失败");
        }
        return ResultUtils.getSuccessResult("删除 用户产品 成功");
    }

    /**
     * 修改 userProd 信息
     * @param userProdBO
     * @return
     */
    @PutMapping
    @Log(operation = "修改 userProd 信息")
    public Result updateUserProd(UserProdBO userProdBO) {
        Boolean update = userProdManage.updateUserProd(userProdBO);
        if (!update) {
            return ResultUtils.getFailedResult(StatusCodeConstant.updateFail, "更新 用户产品 信息失败");
        }
        return ResultUtils.getSuccessResult("更新 用户产品 信息成功");
    }

    /**
     * 获取 userProd 列表
     * @param userProdBO
     * @return
     */
    @GetMapping("")
    @Log(operation = "查询 userProd 列表")
    public Result<List<UserProdDTO>> getUserProdList(UserProdBO userProdBO) {
        List<UserProdDTO> userProdDTOS = userProdManage.listUserProd(userProdBO);
        return ResultUtils.getSuccessResult(userProdDTOS);
    }

    /**
     * 获取 userProd 详情
     * @param id
     * @return
     */
    @GetMapping("/detail")
    @Log(operation = "查询 userProd 详细信息")
    public Result<UserProdDTO> getUserProdDetail(Integer id) {
        UserProdDTO userProdDTO = userProdManage.detailUserProd(id);
        return ResultUtils.getSuccessResult(userProdDTO);
    }

}
