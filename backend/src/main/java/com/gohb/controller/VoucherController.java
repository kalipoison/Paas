package com.gohb.controller;

import com.gohb.aop.anno.Log;
import com.gohb.constant.StatusCodeConstant;
import com.gohb.manage.prod.SkuManage;
import com.gohb.manage.voucher.VoucherManage;
import com.gohb.params.bo.prod.SkuBO;
import com.gohb.params.bo.voucher.VoucherBO;
import com.gohb.params.dto.Result;
import com.gohb.params.dto.ResultUtils;
import com.gohb.params.dto.prod.SkuDTO;
import com.gohb.params.dto.voucher.VoucherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/voucher")
public class VoucherController {


    @Autowired
    private VoucherManage voucherManage;


    /**
     * 新增 兑换券
     * @param userName
     * @param num
     * @return
     */
    @PostMapping
    public Result saveProdProp(String username, Integer num) {
        Boolean save = voucherManage.saveVoucher(username, num);
        if (!save) {
            return ResultUtils.getFailedResult(StatusCodeConstant.createFail, "新增 兑换券 失败");
        }
        return ResultUtils.getSuccessResult("新增 兑换券 成功");
    }

    /**
     * 删除 兑换券
     * @param id
     * @return
     */
    @DeleteMapping()
    public Result deleteProdPropById(Integer id) {
        Boolean delete = voucherManage.deleteVoucher(id);
        if (!delete) {
            return ResultUtils.getFailedResult(StatusCodeConstant.deleteFail, "删除 兑换券 失败");
        }
        return ResultUtils.getSuccessResult("删除 兑换券 成功");
    }

    /**
     * 修改 兑换券
     * @param voucherBO
     * @return
     */
    @PutMapping
    @Log(operation = "修改 兑换券 信息")
    public Result updateProdProp(VoucherBO voucherBO) {
        Boolean update = voucherManage.updateVoucher(voucherBO);
        if (!update) {
            return ResultUtils.getFailedResult(StatusCodeConstant.updateFail, "更新 兑换券 失败");
        }
        return ResultUtils.getSuccessResult("更新 兑换券 成功");
    }

    /**
     * 获取 兑换券
     * @param voucherBO
     * @return
     */
    @GetMapping("")
    @Log(operation = "查询 兑换券 详细信息")
    public Result<List<VoucherDTO>> getVoucherList(VoucherBO voucherBO) {
        List<VoucherDTO> voucherDTOS = voucherManage.listVoucher(voucherBO);
        return ResultUtils.getSuccessResult(voucherDTOS);
    }


}
