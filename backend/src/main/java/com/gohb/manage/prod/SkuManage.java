package com.gohb.manage.prod;

import com.gohb.params.bo.prod.ProdPropValueBO;
import com.gohb.params.bo.prod.SkuBO;
import com.gohb.params.dto.prod.ProdPropValueDTO;
import com.gohb.params.dto.prod.SkuDTO;

import java.util.List;

public interface SkuManage {

    /**
     * 新增 Sku
     * @param skuBO
     * @return
     */
    Boolean saveSku(SkuBO skuBO);

    /**
     * 根据id 删除 Sku
     * @param id
     * @return
     */
    Boolean deleteSku(Integer id);

    /**
     * 更新 Sku
     * @param skuBO
     * @return
     */
    Boolean updateSku(SkuBO skuBO);

    /**
     * 查询所有商品属性值，模糊查询
     * @return
     */
    List<SkuDTO> listSku(SkuBO skuBO);


}
