package com.gohb.manage.prod.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gohb.convert.BoToDtoUtils;
import com.gohb.manage.prod.ProductManage;
import com.gohb.params.bo.prod.ProductBO;
import com.gohb.params.dto.prod.ProductDTO;
import com.gohb.service.prod.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean
public class ProductManageImpl implements ProductManage {

    @Autowired
    private ProductService productService;


    @Override
    public Boolean saveProd(ProductBO productBO) {
        productBO.setCreateTime(new Date());
        productBO.setUpdateTime(new Date());
        productBO.setPutawayTime(new Date());
        productBO.setVersion(0);
        if (productBO.getStatus() == null) {
            productBO.setStatus(0);
        }
        boolean save = productService.save(productBO);
        return save;
    }


    @Override
    public Boolean deleteProd(Integer id) {
        boolean delete = productService.removeById(id);
        return delete;
    }

    @Override
    public Boolean updateProd(ProductBO productBO) {
        productBO.setUpdateTime(new Date());
        productBO.setPutawayTime(new Date());
        boolean update = productService.updateById(productBO);
        return update;
    }

    @Override
    public List<ProductDTO> listProd(ProductBO productBO) {
        List<ProductBO> productBOS = productService.list(new LambdaQueryWrapper<ProductBO>()
                .like(StringUtils.hasText(productBO.getProdName()), ProductBO::getProdName, productBO.getProdName()));
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (ProductBO product : productBOS) {
            productDTOS.add(BoToDtoUtils.productBOTOProductDTO(product));
        }
        return productDTOS;
    }

    @Override
    public ProductDTO detailProduct(Integer productId) {
        ProductBO productBO = productService.getOne(new LambdaQueryWrapper<ProductBO>()
                .eq(ProductBO::getProdId, productId));
        ProductDTO productDTO = BoToDtoUtils.productBOTOProductDTO(productBO);
        return productDTO;
    }
}
