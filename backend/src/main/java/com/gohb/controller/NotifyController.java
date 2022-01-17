package com.gohb.controller;

import com.gohb.aop.anno.Log;
import com.gohb.constant.StatusCodeConstant;
import com.gohb.manage.notify.NotifyManage;
import com.gohb.params.bo.notify.NotifyBO;
import com.gohb.params.bo.prod.ProductBO;
import com.gohb.params.dto.Result;
import com.gohb.params.dto.ResultUtils;
import com.gohb.params.dto.notify.NotifyDTO;
import com.gohb.params.dto.prod.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/notify")
public class NotifyController {

    @Autowired
    private NotifyManage notifyManage;

    /**
     * 新增 notify
     * @param notifyBO
     * @return
     */
    @PostMapping
    public Result saveNotify(NotifyBO notifyBO) {
        Boolean save = notifyManage.saveNotify(notifyBO);
        if (!save) {
            return ResultUtils.getFailedResult(StatusCodeConstant.createFail, "新增 notify 失败");
        }
        return ResultUtils.getSuccessResult("新增 notify 成功");
    }

    /**
     * 删除 notify
     * @param id
     * @return
     */
    @DeleteMapping()
    public Result deleteNotifyById(Integer id) {
        Boolean delete = notifyManage.deleteNotify(id);
        if (!delete) {
            return ResultUtils.getFailedResult(StatusCodeConstant.deleteFail, "删除 notify 失败");
        }
        return ResultUtils.getSuccessResult("删除 notify 成功");
    }

    /**
     * 修改 notify 信息
     * @param notifyBO
     * @return
     */
    @PutMapping
    @Log(operation = "修改 notify 信息")
    public Result updateNotify(NotifyBO notifyBO) {
        Boolean update = notifyManage.updateNotify(notifyBO);
        if (!update) {
            return ResultUtils.getFailedResult(StatusCodeConstant.updateFail, "更新 notify 信息失败");
        }
        return ResultUtils.getSuccessResult("更新 notify 信息成功");
    }

    /**
     * 获取 notify 列表
     * @param notifyBO
     * @return
     */
    @GetMapping("")
    @Log(operation = "查询 notify 列表")
    public Result<List<NotifyDTO>> getProductList(NotifyBO notifyBO) {
        List<NotifyDTO> notifyDTOS = notifyManage.listNotify(notifyBO);
        return ResultUtils.getSuccessResult(notifyDTOS);
    }

    /**
     * 获取 notify 详情
     * @param id
     * @return
     */
    @GetMapping("/detail")
    @Log(operation = "查询 notify 详细信息")
    public Result<NotifyDTO> getNotifyDetail(Integer id) {
        NotifyDTO notifyDTO = notifyManage.detailNotify(id);
        return ResultUtils.getSuccessResult(notifyDTO);
    }



}
