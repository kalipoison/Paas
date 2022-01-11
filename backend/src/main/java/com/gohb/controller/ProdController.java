package com.gohb.controller;

import com.gohb.aop.anno.Log;
import com.gohb.constant.StatusCodeConstant;
import com.gohb.manage.prod.ProdManage;
import com.gohb.manage.prod.ProdPropManage;
import com.gohb.params.bo.prod.ProdBO;
import com.gohb.params.bo.prod.ProdPropBO;
import com.gohb.params.dto.Result;
import com.gohb.params.dto.ResultUtils;
import com.gohb.params.dto.prod.ProdDTO;
import com.gohb.params.dto.prod.ProdPropDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/prod")
public class ProdController {


    @Autowired
    private ProdManage prodManage;


    /**
     * 新增商品
     * @param prodBO
     * @return
     */
    @PostMapping
    public Result saveProdProp(ProdBO prodBO) {
        Boolean save = prodManage.saveProd(prodBO);
        if (!save) {
            return ResultUtils.getFailedResult(StatusCodeConstant.createFail, "新增商品失败");
        }
        return ResultUtils.getSuccessResult("新增商品成功");
    }

    /**
     * 删除商品
     * @param id
     * @return
     */
    @DeleteMapping()
    public Result deleteProdPropById(Integer id) {
        Boolean delete = prodManage.deleteProd(id);
        if (!delete) {
            return ResultUtils.getFailedResult(StatusCodeConstant.deleteFail, "删除商品失败");
        }
        return ResultUtils.getSuccessResult("删除商品成功");
    }

    /**
     * 修改商品属性信息
     * @param prodBO
     * @return
     */
    @PutMapping
    @Log(operation = "修改商品信息")
    public Result updateProdProp(ProdBO prodBO) {
        Boolean update = prodManage.updateProd(prodBO);
        if (!update) {
            return ResultUtils.getFailedResult(StatusCodeConstant.updateFail, "更新商品信息失败");
        }
        return ResultUtils.getSuccessResult("更新商品信息成功");
    }

    /**
     * 获取商品属性详细信息
     * @param prodBO
     * @return
     */
    @GetMapping("detail")
    @Log(operation = "查询商品属性详细信息")
    public Result<List<ProdDTO>> getProdPropDetail(ProdBO prodBO) {
        List<ProdDTO> prodDTOS = prodManage.listProd(prodBO);
        return ResultUtils.getSuccessResult(prodDTOS);
    }


}
