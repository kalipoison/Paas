package com.gohb.controller;

import com.gohb.aop.anno.Log;
import com.gohb.constant.StatusCodeConstant;
import com.gohb.manage.prod.ProdPropManage;
import com.gohb.manage.prod.SkuManage;
import com.gohb.params.bo.prod.ProdPropBO;
import com.gohb.params.bo.prod.SkuBO;
import com.gohb.params.dto.Result;
import com.gohb.params.dto.ResultUtils;
import com.gohb.params.dto.prod.ProdPropDTO;
import com.gohb.params.dto.prod.SkuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/sku")
public class SkuController {


    @Autowired
    private SkuManage skuManage;


    /**
     * 新增 SKU
     * @param skuBO
     * @return
     */
    @PostMapping
    public Result saveProdProp(SkuBO skuBO) {
        Boolean save = skuManage.saveSku(skuBO);
        if (!save) {
            return ResultUtils.getFailedResult(StatusCodeConstant.createFail, "新增 SKU 失败");
        }
        return ResultUtils.getSuccessResult("新增 SKU 成功");
    }

    /**
     * 删除 SKU
     * @param id
     * @return
     */
    @DeleteMapping()
    public Result deleteProdPropById(Integer id) {
        Boolean delete = skuManage.deleteSku(id);
        if (!delete) {
            return ResultUtils.getFailedResult(StatusCodeConstant.deleteFail, "删除 SKU 失败");
        }
        return ResultUtils.getSuccessResult("删除 SKU 成功");
    }

    /**
     * 修改 SKU
     * @param skuBO
     * @return
     */
    @PutMapping
    @Log(operation = "修改SKU信息")
    public Result updateProdProp(SkuBO skuBO) {
        Boolean update = skuManage.updateSku(skuBO);
        if (!update) {
            return ResultUtils.getFailedResult(StatusCodeConstant.updateFail, "更新 SKU 失败");
        }
        return ResultUtils.getSuccessResult("更新 SKU 成功");
    }

    /**
     * 获取 SKU 列表
     * @param skuBO
     * @return
     */
    @GetMapping("")
    @Log(operation = "查询 SKU 列表")
    public Result<List<SkuDTO>> getSkuList(SkuBO skuBO) {
        List<SkuDTO> skuDTOS = skuManage.listSku(skuBO);
        return ResultUtils.getSuccessResult(skuDTOS).setCount(Long.valueOf(skuDTOS.size()));
    }




}
