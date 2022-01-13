package com.gohb.controller;

import com.gohb.aop.anno.Log;
import com.gohb.constant.StatusCodeConstant;
import com.gohb.manage.prod.ProdPropManage;
import com.gohb.params.bo.prod.ProdPropBO;
import com.gohb.params.bo.sys.SysRoleBO;
import com.gohb.params.dto.Result;
import com.gohb.params.dto.ResultUtils;
import com.gohb.params.dto.prod.ProdPropDTO;
import com.gohb.params.dto.sys.SysRoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/prodProp")
public class ProdPropController {


    @Autowired
    private ProdPropManage prodPropManage;


    /**
     * 新增商品属性
     * @param prodPropBO
     * @return
     */
    @PostMapping
    public Result saveProdProp(ProdPropBO prodPropBO) {
        Boolean save = prodPropManage.saveProdProp(prodPropBO);
        if (!save) {
            return ResultUtils.getFailedResult(StatusCodeConstant.createFail, "新增商品属性失败");
        }
        return ResultUtils.getSuccessResult("新增商品属性成功");
    }

    /**
     * 删除商品属性
     * @param propId
     * @return
     */
    @DeleteMapping()
    public Result deleteProdPropById(Integer propId) {
        Boolean delete = prodPropManage.deleteProdProp(propId);
        if (!delete) {
            return ResultUtils.getFailedResult(StatusCodeConstant.deleteFail, "删除商品属性失败");
        }
        return ResultUtils.getSuccessResult("删除商品属性成功");
    }

    /**
     * 修改商品属性信息
     * @param prodPropBO
     * @return
     */
    @PutMapping
    @Log(operation = "修改商品属性信息")
    public Result updateProdProp(ProdPropBO prodPropBO) {
        Boolean update = prodPropManage.updateProdProp(prodPropBO);
        if (!update) {
            return ResultUtils.getFailedResult(StatusCodeConstant.updateFail, "更新商品属性信息失败");
        }
        return ResultUtils.getSuccessResult("更新商品属性信息成功");
    }

    /**
     * 获取商品属性 列表
     * @param prodPropBO
     * @return
     */
    @GetMapping("")
    @Log(operation = "查询商品属性 列表")
    public Result<List<ProdPropDTO>> getProdPropList(ProdPropBO prodPropBO) {
        List<ProdPropDTO> prodPropDTOS = prodPropManage.listProdProp(prodPropBO);
        return ResultUtils.getSuccessResult(prodPropDTOS);
    }


    /**
     * 获取商品属性详细信息
     * @param propId
     * @return
     */
    @GetMapping("/detail")
    @Log(operation = "查询商品属性详细信息")
    public Result<ProdPropDTO> getProdPropDetail(Integer propId) {
        ProdPropDTO prodPropDTO = prodPropManage.prodPropDetail(propId);
        return ResultUtils.getSuccessResult(prodPropDTO);
    }

}
