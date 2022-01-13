package com.gohb.manage.prod;

import com.gohb.params.bo.prod.ProductBO;
import com.gohb.params.dto.prod.ProductDTO;

import java.util.List;

public interface ProductManage {

    /**
     * 新增商品
     * @param prodBO
     * @return
     */
    Boolean saveProd(ProductBO prodBO);


    /**
     * 根据id 删除商品
     * @param id
     * @return
     */
    Boolean deleteProd(Integer id);

    /**
     * 更新 商品
     * @param prodBO
     * @return
     */
    Boolean updateProd(ProductBO prodBO);

    /**
     * 查询所有商品，模糊查询
     * @return
     */
    List<ProductDTO> listProd(ProductBO prodBO);

    /**
     * 查询商品详情
     * @return
     */
    ProductDTO detailProduct(Integer productId);

}
