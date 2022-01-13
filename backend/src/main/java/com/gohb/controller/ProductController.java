package com.gohb.controller;

import com.gohb.aop.anno.Log;
import com.gohb.constant.StatusCodeConstant;
import com.gohb.manage.prod.ProductManage;
import com.gohb.params.bo.prod.ProductBO;
import com.gohb.params.dto.Result;
import com.gohb.params.dto.ResultUtils;
import com.gohb.params.dto.prod.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/product")
public class ProductController {


    @Autowired
    private ProductManage productManage;


    /**
     * 新增商品
     * @param prodBO
     * @return
     */
    @PostMapping
    public Result saveProdProp(ProductBO prodBO) {
        Boolean save = productManage.saveProd(prodBO);
        if (!save) {
            return ResultUtils.getFailedResult(StatusCodeConstant.createFail, "新增商品失败");
        }
        return ResultUtils.getSuccessResult("新增商品成功");
    }

    /**
     * 删除商品
     * @param productId
     * @return
     */
    @DeleteMapping()
    public Result deleteProdPropById(Integer productId) {
        Boolean delete = productManage.deleteProd(productId);
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
    public Result updateProdProp(ProductBO prodBO) {
        Boolean update = productManage.updateProd(prodBO);
        if (!update) {
            return ResultUtils.getFailedResult(StatusCodeConstant.updateFail, "更新商品信息失败");
        }
        return ResultUtils.getSuccessResult("更新商品信息成功");
    }

    /**
     * 获取商品列表
     * @param prodBO
     * @return
     */
    @GetMapping("")
    @Log(operation = "查询商品列表")
    public Result<List<ProductDTO>> getProductList(ProductBO prodBO) {
        List<ProductDTO> productDTOS = productManage.listProd(prodBO);
        return ResultUtils.getSuccessResult(productDTOS);
    }

    /**
     * 获取商品 详情
     * @param productId
     * @return
     */
    @GetMapping("/detail")
    @Log(operation = "查询商品详细信息")
    public Result<ProductDTO> getProdPropDetail(Integer productId) {
        ProductDTO productDTO = productManage.detailProduct(productId);
        return ResultUtils.getSuccessResult(productDTO);
    }


}
