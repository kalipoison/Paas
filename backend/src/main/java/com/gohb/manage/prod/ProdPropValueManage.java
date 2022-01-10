package com.gohb.manage.prod;

import com.gohb.params.bo.prod.ProdPropBO;
import com.gohb.params.bo.prod.ProdPropValueBO;
import com.gohb.params.dto.prod.ProdPropValueDTO;

import java.util.List;

public interface ProdPropValueManage {

    /**
     * 新增商品属性值
     * @param prodPropValueBO
     * @return
     */
    Boolean saveProdPropValue(ProdPropValueBO prodPropValueBO);

    /**
     * 根据id 删除商品属性值
     * @param id
     * @return
     */
    Boolean deleteProdPropValue(Integer id);

    /**
     * 更新 商品属性值
     * @param prodPropValueBO
     * @return
     */
    Boolean updateProdPropValue(ProdPropValueBO prodPropValueBO);

    /**
     * 查询所有商品属性值，模糊查询
     * @return
     */
    List<ProdPropValueDTO> listProdPropValue(ProdPropValueBO prodPropValueBO);

}
