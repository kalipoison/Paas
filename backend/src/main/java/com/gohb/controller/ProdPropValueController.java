package com.gohb.controller;

import com.gohb.aop.anno.Log;
import com.gohb.constant.StatusCodeConstant;
import com.gohb.manage.prod.ProdPropValueManage;
import com.gohb.params.bo.prod.ProdPropBO;
import com.gohb.params.bo.prod.ProdPropValueBO;
import com.gohb.params.dto.Result;
import com.gohb.params.dto.ResultUtils;
import com.gohb.params.dto.prod.ProdPropDTO;
import com.gohb.params.dto.prod.ProdPropValueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/prodPropValue")
public class ProdPropValueController {

    @Autowired
    private ProdPropValueManage prodPropValueManage;

    /**
     * 新增商品属性值
     * @param prodPropValueBO
     * @return
     */
    @PostMapping
    public Result saveProdProp(ProdPropValueBO prodPropValueBO) {
        Boolean save = prodPropValueManage.saveProdPropValue(prodPropValueBO);
        if (!save) {
            return ResultUtils.getFailedResult(StatusCodeConstant.createFail, "新增商品属性值失败");
        }
        return ResultUtils.getSuccessResult("新增商品属性值成功");
    }

    /**
     * 删除商品属性
     * @param valueId
     * @return
     */
    @DeleteMapping()
    public Result deleteProdPropById(Integer valueId) {
        Boolean delete = prodPropValueManage.deleteProdPropValue(valueId);
        if (!delete) {
            return ResultUtils.getFailedResult(StatusCodeConstant.deleteFail, "删除商品属性值失败");
        }
        return ResultUtils.getSuccessResult("删除商品属性值成功");
    }

    /**
     * 修改商品属性信息
     * @param prodPropValueBO
     * @return
     */
    @PutMapping
    @Log(operation = "修改商品属性值信息")
    public Result updateProdProp(ProdPropValueBO prodPropValueBO) {
        Boolean update = prodPropValueManage.updateProdPropValue(prodPropValueBO);
        if (!update) {
            return ResultUtils.getFailedResult(StatusCodeConstant.updateFail, "更新商品属性值信息失败");
        }
        return ResultUtils.getSuccessResult("更新商品属性值信息成功");
    }

    /**
     * 获取商品属性值列表
     * @param prodPropValueBO
     * @return
     */
    @GetMapping("")
    @Log(operation = "查询商品属性值列表")
    public Result<List<ProdPropValueDTO>> getProdPropList(ProdPropValueBO prodPropValueBO) {
        List<ProdPropValueDTO> prodPropValueDTOS = prodPropValueManage.listProdPropValue(prodPropValueBO);
        return ResultUtils.getSuccessResult(prodPropValueDTOS).setCount(Long.valueOf(prodPropValueDTOS.size()));
    }


    /**
     * 获取商品属性值详情
     * @param valueId
     * @return
     */
    @GetMapping("/detail")
    @Log(operation = "查询商品属性值详细信息")
    public Result<ProdPropValueDTO> getProdPropList(Integer valueId) {
        ProdPropValueDTO prodPropValueDTO = prodPropValueManage.detailProdPropValue(valueId);
        return ResultUtils.getSuccessResult(prodPropValueDTO);
    }


}
